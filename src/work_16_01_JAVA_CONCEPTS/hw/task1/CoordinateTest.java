package work_16_01_JAVA_CONCEPTS.hw.task1;

public class CoordinateTest {
    public static void main(String[] args) {
        Coordinate coordinate = new Coordinate(25, 50);
        Coordinate coordinate2 = new Coordinate(25, 50);
        System.out.println(coordinate.toString());
        boolean equals = coordinate.equals(coordinate2);
        System.out.println(equals);
        System.out.println(coordinate2.hashCode());
        System.out.println(coordinate.hashCode());
    }
}
