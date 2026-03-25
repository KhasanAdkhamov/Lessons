package Enum.hw.task3;

import java.util.Arrays;
import java.util.List;

public enum Season {
    WINTER,
    SPRING,
    SUMMER,
    AUTUMN;

    public Season next() {
        Season[] values = Season.values();
        int i = (this.ordinal() + 1) % values.length;
        return values[i];
    }

    public List<String> getMonths() {
        switch (this) {
            case WINTER: return Arrays.asList("Декабрь", "Январь", "Февраль");
            case SPRING: return Arrays.asList("Март", "Апрель", "Май");
            case SUMMER: return Arrays.asList("Июнь", "Июль", "Август");
            case AUTUMN: return Arrays.asList("Сентябрь", "Октябрь", "Ноябрь");
            default: throw new IllegalArgumentException("");
        }
    }
}
