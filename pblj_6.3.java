import java.util.*;
import java.util.stream.Collectors;

class Product {
    String name;
    String category;
    double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
    public String toString() {
        return name + " ($" + price + ")";
    }
}

public class ProductStreamProcessing {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 800),
            new Product("Smartphone", "Electronics", 600),
            new Product("Headphones", "Electronics", 150),
            new Product("Table", "Furniture", 200),
            new Product("Chair", "Furniture", 100),
            new Product("Sofa", "Furniture", 1200),
            new Product("Rice", "Grocery", 50),
            new Product("Milk", "Grocery", 30),
            new Product("Apples", "Grocery", 40)
        );
        Map<String, List<Product>> groupedByCategory = products.stream()
            .collect(Collectors.groupingBy(p -> p.category));
        Map<String, Optional<Product>> mostExpensiveInCategory = products.stream()
            .collect(Collectors.groupingBy(
                p -> p.category, 
                Collectors.maxBy(Comparator.comparingDouble(Product::getPrice))
            ));
        double averagePrice = products.stream()
            .mapToDouble(Product::getPrice)
            .average()
            .orElse(0.0);
        System.out.println("Products Grouped by Category:");
        groupedByCategory.forEach((category, productList) -> 
            System.out.println(category + ": " + productList));

        System.out.println("\nMost Expensive Product in Each Category:");
        mostExpensiveInCategory.forEach((category, product) -> 
            System.out.println(category + ": " + product.orElse(null)));

        System.out.println("\nAverage Price of All Products: $" + String.format("%.2f", averagePrice));
    }
}