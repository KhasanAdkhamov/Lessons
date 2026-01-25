package work_16_01_JAVA_CONCEPTS.hw.task3;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

//Неизменяемый класс для диапазона дат. Методы: contains(date), overlaps(other), getDays().
public class DateRange {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public DateRange(LocalDate startDate,LocalDate endDate) {
        if (startDate == null || endDate == null) {
            throw new NullPointerException("дата не можем быть пустой");
        }
        if (startDate.isAfter(endDate)) {
            throw new RuntimeException("конечная дата не может быть после начальной");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean contains(LocalDate date) {
        if (date == null) {
            throw new NullPointerException("пустая дата");
        }
        if (!date.isAfter(startDate) && !date.isBefore(endDate)) {
            throw new RuntimeException("ошибка");
        }
        return true;
    }

    public long getDays() {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        if (this == o) return true;
        DateRange dateRange = (DateRange) o;
        return Objects.equals(startDate, dateRange.startDate) && Objects.equals(endDate, dateRange.endDate);
    }

    @Override
    public int hashCode() {
        int result = 3;
        result = 7 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 7 * result + (endDate != null ? endDate.hashCode(): 0);

        return result;
    }

    @Override
    public String toString() {
        return String.format("DataRange: начальная дата " + startDate +
                " конечная дата " + endDate);
    }
}
