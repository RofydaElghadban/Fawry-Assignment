package PackageOne;

import PackageOne.exception.*;
import PackageOne.model.Customer;
import PackageOne.model.GeneralProduct;
import PackageOne.repository.Inventory;
import PackageOne.service.Cart;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("Rofyda Mohamed Elghadban", 2000);
        System.out.println("Welcome " + customer.getName() + " to our stock!");

        System.out.printf("Your balance: %.2f %n%n", customer.getBalance());

        Cart cart = new Cart();
        Inventory inventory = new Inventory();

        GeneralProduct cheese = new GeneralProduct("Cheese", 75, 10, true,
                LocalDate.of(2025, 7, 20), true, 258.8);

        GeneralProduct biscuit = new GeneralProduct("Biscuit", 23, 4, true,
                LocalDate.of(2025, 7, 30), true, 2.5);

        GeneralProduct tv = new GeneralProduct("TV", 800, 5, false,
                null, true, 5000);

        GeneralProduct scratchCard = new GeneralProduct("Scratch Card", 50, 20,
                false, null, false, 0);


        cheese.setDiscount(10);
        tv.setDiscount(200);

        inventory.addProduct(cheese);
        inventory.addProduct(biscuit);
        inventory.addProduct(tv);
        inventory.addProduct(scratchCard);

        inventory.printAllProducts();

        try {
            addToCartSafely(cart, inventory, "Cheese", 3);
            addToCartSafely(cart, inventory, "Biscuit", 2);
            addToCartSafely(cart, inventory, "TV", 1);
            addToCartSafely(cart, inventory, "Scratch Card", 1);

            cart.checkout(customer);

        } catch (EmptyCartException | QuantityExceededException |
                 ProductExpiredException | BalanceExceededException |
                 ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addToCartSafely(Cart cart, Inventory inventory, String productName, int quantity) {
        if (!inventory.containsProduct(productName)) {
            throw new ProductNotFoundException(productName+ " not found in stock.");
        }
        cart.add(inventory.getProductByName(productName), quantity);
    }
}
