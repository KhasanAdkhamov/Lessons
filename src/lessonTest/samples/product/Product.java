package lessonTest.samples.product;

public class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        setName(name);
        setPrice(price);
        setQuantity(quantity);
    }

    public double getTotalValue() {
        return price * quantity;
    }

    public void sell(int amount) {
        if(quantity == 0 || quantity < amount) {
            throw new IllegalArgumentException("product is 0");
        }
        quantity -= amount;
        System.out.println(quantity);
    }

    public void restock(int amount) {
        quantity += amount;
        System.out.println(quantity);
    }

    public void setName(String name) {
        if(name.isEmpty()) {
            throw new RuntimeException("name must not be empty");
        }
        this.name = name;
    }

    public void setPrice(double price) {
        if(price <= 0.0) {
            throw new RuntimeException("price should be more 0");
        }
        this.price = price;
    }

    public void setQuantity(int quantity) {
        if(quantity < 0) {
            throw new RuntimeException("it should be positive amount, more 0");
        }
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
