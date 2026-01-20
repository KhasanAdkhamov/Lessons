package work_16_01_JAVA_CONCEPTS.hw.task1;
//Широта и долгота. Переопредели все три метода Object.

import java.util.Objects;

public class Coordinate {
    private int latitude;
    private int longitude;

    public Coordinate(int latitude, int longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;

        Coordinate other = (Coordinate) o;
        return this.latitude == other.latitude
                && this.longitude == other.longitude;
    }

    @Override
    public int hashCode() {
        int result = 43;

        result = 11 * result + latitude;
        result = 11 * result + longitude;

        return result;
    }

    @Override
    public String toString() {
        return String.format("Координаты широты: %d, долготы: %d", latitude, longitude);
    }
}
