package work_16_01_JAVA_CONCEPTS.hw.task4;

import java.util.Objects;

public class ColoredPoint extends Point{
    private final String color;

    public ColoredPoint(int x, int y, String color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public boolean canEqual(Object other) {
        return other instanceof ColoredPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ColoredPoint)) return false;
        ColoredPoint that = (ColoredPoint) o;
        if (!that.canEqual(this)) return false;
        return super.equals(o) && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        int result = 13;
        result = 3 * result + getX();
        result = 3 * result + getY();
        result = 3 * result +(color != null ? color.hashCode(): 0);
        return result;
    }

    @Override
    public String toString() {
        return "ColoredPoint{" +
                "color='" + color + '\'' +
                '}';
    }
}
