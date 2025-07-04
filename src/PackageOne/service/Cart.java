package PackageOne.service;

import PackageOne.exception.BalanceExceededException;
import PackageOne.exception.EmptyCartException;
import PackageOne.exception.ProductExpiredException;
import PackageOne.exception.QuantityExceededException;
import PackageOne.model.Customer;
import PackageOne.model.Product;
import PackageOne.model.Shippable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Cart {
    private final Map<Product, Integer> items = new HashMap<>();

    public void add(Product product, int quantity) {
        if (product.getQuantity() < quantity) {
            throw new QuantityExceededException("Not enough quantity available for " + product.getName() +
                    ".\nRequested: " + quantity + ", Available: " + product.getQuantity());
        }
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public void checkout(Customer customer) {
        if (items.isEmpty()) {
            throw new EmptyCartException("Cart is empty!");
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        String formattedDate = now.format(formatter);

        double subtotal = 0;
        double discount = 0;
        double shippingFee = 0;
        Map<Shippable, Integer> shippables = new HashMap<>();

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product.isExpired()) {
                throw new ProductExpiredException("Product " + product.getName() + " expired");
            }

            if (product.getQuantity() < quantity) {
                throw new QuantityExceededException("Not enough quantity for: " + product.getName());
            }

            if (product.isShippable() && product instanceof Shippable s) {
                shippables.put(s, shippables.getOrDefault(s, 0) + quantity);
                shippingFee += s.getWeight() * quantity * 10 / 1000.0;
            }
        }
        if (!shippables.isEmpty()) {
            ShippingService.ship(shippables);
        }

        System.out.println("** Checkout receipt **");
        System.out.printf("%-6s %-20s %-12s %-10s %-10s%n", "Qty", "Product", "Unit Price", "Discount", "Total");
        System.out.println("-------------------------------------------------------------");

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product p = entry.getKey();
            int qty = entry.getValue();

            double originalPrice = p.getPrice();
            double finalPrice = p.getFinalPrice();
            double discountPerItem = originalPrice - finalPrice;

            subtotal += finalPrice * qty;
            discount += discountPerItem * qty;

            System.out.printf("%-6s %-20s %-12.2f %-10.2f %-10.2f%n",
                    qty + "x", p.getName(), originalPrice, -discountPerItem, finalPrice * qty);
        }


        double amount = subtotal + shippingFee;

        if (!customer.deductBalance(amount, discount)) {
            throw new BalanceExceededException("Not enough balance to complete the order");
        }

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            entry.getKey().decreaseQuantity(entry.getValue());
        }

        System.out.println("-------------------------------------------------------------");
        System.out.println();
        System.out.printf("%-25s %.2f %n", "Subtotal", subtotal);
        System.out.printf("%-25s %.2f %n", "Shipping", shippingFee);
        System.out.printf("%-25s -%.2f %n", "Discount", discount);
        System.out.printf("%-25s %.2f %n", "Amount", amount-discount);
        System.out.printf("%-25s %.2f %n%n", "Remaining balance", customer.getBalance());
        System.out.println("Checkout Date: " + formattedDate);
        System.out.println("Thank you for shopping with us!");

        items.clear();
    }
}
