import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - " + price + " руб.";
    }
}

public class SalesTracker {
    private ArrayList<Product> soldProducts;

    public SalesTracker() {
        soldProducts = new ArrayList<>();
    }

    public void addProduct(Product product) {
        soldProducts.add(product);
    }

    public void displaySoldProducts() {
        if (soldProducts.isEmpty()) {
            System.out.println("Нет проданных товаров.");
            return;
        }
        System.out.println("Список проданных товаров:");
        for (Product p : soldProducts) {
            System.out.println(p);
        }
    }

    public double getTotalSales() {
        double total = 0;
        for (Product p : soldProducts) {
            total += p.getPrice();
        }
        return total;
    }

    public String getMostPopularProduct() {
        if (soldProducts.isEmpty()) {
            return "Нет данных";
        }
        Map<String, Integer> frequency = new HashMap<>();
        for (Product p : soldProducts) {
            frequency.put(p.getName(), frequency.getOrDefault(p.getName(), 0) + 1);
        }
        String mostPopular = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostPopular = entry.getKey();
            }
        }
        return mostPopular + " (продано " + maxCount + " раз)";
    }

    public static void main(String[] args) {
        SalesTracker tracker = new SalesTracker();
        
        tracker.addProduct(new Product("Молоко", 80));
        tracker.addProduct(new Product("Хлеб", 50));
        tracker.addProduct(new Product("Молоко", 80));
        tracker.addProduct(new Product("Яйца", 100));
        tracker.addProduct(new Product("Хлеб", 50));

        tracker.displaySoldProducts();
        System.out.println("Общая сумма продаж: " + tracker.getTotalSales() + " руб.");
        System.out.println("Самый популярный товар: " + tracker.getMostPopularProduct());
    }
}