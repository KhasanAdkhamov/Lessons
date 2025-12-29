package lessonTest.homework.tasks2;

public class ProductTest {
    public static void main(String[] args) {
        Product product = new Product("Book", 2.5, 5);
        System.out.println(product.getTotalValue());
        product.sell(1);
        product.restock(4);
    }
}
