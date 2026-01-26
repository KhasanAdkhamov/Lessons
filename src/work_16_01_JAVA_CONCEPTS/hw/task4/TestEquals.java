package work_16_01_JAVA_CONCEPTS.hw.task4;

public class TestEquals {
    public static void main(String[] args) {
        Point point1 = new Point(1, 2);
        ColoredPoint coloredPoint1 = new ColoredPoint(1, 2, "red");
        Point point2 = new Point(1, 2);
        ColoredPoint coloredPoint2 = new ColoredPoint(1, 2, "red");
        System.out.println(point1.equals(point2));
        System.out.println(point1.equals(coloredPoint1));
        System.out.println(point1.hashCode());
        System.out.println(coloredPoint1.hashCode());
        System.out.println(coloredPoint2.equals(coloredPoint1));


        Point2 point3 = new Point2(1, 2);
        Point2 point4 = new Point2(1, 2);
        ColoredPoint2 green = new ColoredPoint2(1, 2, "green");
        System.out.println("test без canEqual");
        System.out.println(point3.equals(green));
        System.out.println(green.equals(point3));


    }
}
