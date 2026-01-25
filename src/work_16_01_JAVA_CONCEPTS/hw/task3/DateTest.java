package work_16_01_JAVA_CONCEPTS.hw.task3;

import java.time.LocalDate;

public class DateTest {
    public static void main(String[] args) {
        DateRange dateRange = new DateRange(LocalDate.of(2021, 2, 1), LocalDate.of(2021, 2, 20));
        System.out.println(dateRange.contains(LocalDate.of(2021, 2, 15)));
        System.out.println(dateRange);
        System.out.println(dateRange.getDays());
    }
}
