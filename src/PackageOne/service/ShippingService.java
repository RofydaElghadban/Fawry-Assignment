package PackageOne.service;

import PackageOne.model.Shippable;

import java.util.Map;

public class ShippingService {
    public static void ship(Map<Shippable, Integer> items) {
        System.out.println("** Shipment Notice **");
        System.out.printf("%-6s %-25s %-15s%n", "Qty", "Product", "Weight (g)");
        System.out.println("------------------------------------------------------");

        double totalWeight = 0;

        for (Map.Entry<Shippable, Integer> entry : items.entrySet()) {
            Shippable product = entry.getKey();
            int count = entry.getValue();
            double weight = product.getWeight() * count;

            if (weight > 0) {
                System.out.printf("%-6s %-25s %-15.2f%n", count + "x", product.getName(), weight);
                totalWeight += weight;
            }
        }
        System.out.printf("%-32s %.2f kg%n", "Total package weight:", totalWeight / 1000);
        System.out.println("------------------------------------------------------\n");
    }
}
