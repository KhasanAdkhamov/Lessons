package Functional.HW.task6;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Топ-N элементов
//
//Напиши метод, который принимает список элементов и число N, и возвращает N самых часто встречающихся элементов.
public class TopNelement {
    public static void main(String[] args) {
        System.out.println(getElement(List.of(1, 1, 2, 3, 3, 4, 4, 4, 5, 6), 3));

    }

    public static <T> List<T> getElement(List<T> items, int number) {
        return items.stream()
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<T, Long>comparingByValue().reversed())
                .limit(number)
                .map(Map.Entry::getKey)
                .toList();

    }
}
