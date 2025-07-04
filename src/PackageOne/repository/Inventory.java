package PackageOne.repository;

import PackageOne.exception.ProductNotFoundException;
import PackageOne.model.Product;

import java.util.*;

public class Inventory {
    private final Map<String, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.getName().toLowerCase(), product);
    }

    public Product getProductByName(String name) {
        return products.get(name.toLowerCase());
    }

    public boolean containsProduct(String name) {
        return products.containsKey(name.toLowerCase());
    }

    public void printAllProducts() {
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products in stock.");
        }

        System.out.println("** Available Products **");
        System.out.printf("%-25s  %-8s  %-8s  %-8s%n", "Product", "Price", "Quantity", "Discount");
        System.out.println("---------------------------------------------------------");

        for (Product p : products.values()) {
            System.out.printf("%-25s  %-8.2f  %-8d  %-8.2f%n",
                    p.getName(), p.getPrice(), p.getQuantity(), p.getDiscount());
        }
        System.out.println("---------------------------------------------------------");
        System.out.println();
    }

}
