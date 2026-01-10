package work_07_01_26.homework;

import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ProductCatalog {
    private ArrayList<Product> products;

    public ProductCatalog() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product p) {
        products.add(p);
        System.out.println(p.getName());
    }

    public void sortByPrice() {
        Collections.sort(products);
        System.out.println(products);
    }

    public void sortByRating() {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return Integer.compare(o2.getRating(), o1.getRating());
            }
        });
    }

    public Product findCheapest() {
        Product cheapest = products.get(0);
        for (Product product : products) {
            if (cheapest.getPrice() > product.getPrice()) {
                cheapest = product;
            }
        }
        return cheapest;
    }

    public void displayAll() {
        for (Product product : products) {
            product.displayIngo();
        }
    }

}
