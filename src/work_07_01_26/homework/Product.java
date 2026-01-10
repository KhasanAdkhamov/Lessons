package work_07_01_26.homework;

public class Product implements Comparable<Product> {
    private String name;
    private double price;
    private int rating;

    public Product(String name, double price, int rating) {
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public void displayIngo() {
        System.out.println(toString());
    }

    public void setRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new RuntimeException("1-5");
        }
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                '}';
    }

    @Override
    public int compareTo(Product o) {
        return Double.compare(this.price, o.price);
    }

}
