package lessonTest.samples.rectangle;

public class RectangleTest {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(5);
        rectangle.setHeight(2);
        System.out.println(rectangle.getArea());
        System.out.println(rectangle.isSquare());
    }
}
