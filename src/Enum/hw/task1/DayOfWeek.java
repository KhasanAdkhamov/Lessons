package Enum.hw.task1;

public enum DayOfWeek {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    public boolean isWeekend() {
        return this == SATURDAY || this == SUNDAY;
    }

    public boolean isWeekDay() {
        if (this == SATURDAY || this ==SUNDAY) {
            return false;
        }
        return true;
    }
}
