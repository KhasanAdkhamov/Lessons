package Functional.HW.task1.task3;

import java.util.List;
import java.util.OptionalDouble;

public class AverageLen {
    public static void main(String[] args) {
        List<String> strings = List.of("Hello", "World", "Java", "Stream");
        List<String>  strings2 = List.of("", "", "");

        getAverage(strings);
        getAverage(strings2);

    }

    public static void getAverage(List<String> strings) {
        OptionalDouble average = strings.stream()
                .mapToDouble(String::length)
                .average();
        average.ifPresentOrElse(System.out::println, () -> System.out.println("0"));

    }
}
