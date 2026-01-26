package work_16_01_JAVA_CONCEPTS.hw.task4;

import java.util.Objects;

public class Point2 {
    private final int x;
    private final int y;

    public Point2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point2)) return false;
        Point2 point2 = (Point2) o;
        return x == point2.x && y == point2.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
