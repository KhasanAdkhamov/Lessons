package Functional.HW.task2;

import java.util.Comparator;
import java.util.List;

public class SortByName {
    public static void main(String[] args) {
        List<String> names = List.of("Аня", "Боря", "Алексей", "Борис", "Анна");
        getSortedName(names);
    }

    public static void getSortedName(List<String> names) {
        names.stream()
                .sorted(Comparator.comparing(n-> n.charAt(0)))
                .forEach(System.out::println);
    }
}
