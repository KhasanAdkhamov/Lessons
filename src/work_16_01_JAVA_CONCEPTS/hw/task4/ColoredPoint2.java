package work_16_01_JAVA_CONCEPTS.hw.task4;

import java.util.Objects;

public class ColoredPoint2 extends Point2{
    private final String color;

    public ColoredPoint2(int x, int y, String color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point2)) return false;
        ColoredPoint2 other = (ColoredPoint2) o;
        return super.equals(o) && Objects.equals(color, other.color);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String getColor() {
        return color;
    }
}
