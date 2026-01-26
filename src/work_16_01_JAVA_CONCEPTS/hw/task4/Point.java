package work_16_01_JAVA_CONCEPTS.hw.task4;

import java.util.Objects;

//ДЗ 4: Equals с наследованием (⭐⭐⭐)
//
//Класс ColoredPoint extends Point. Как правильно реализовать equals()? Исследуй проблему.
public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean canEqual(Object other) {
        return other instanceof Point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point other = (Point) o;
        if (!other.canEqual(this)) return false;
        return x == other.x && y == other.y;
    }

    @Override
    public int hashCode() {
        int result = 13;
        result = 3 * result + x;
        result = 3 * result + y;

        return result;
    }
}
