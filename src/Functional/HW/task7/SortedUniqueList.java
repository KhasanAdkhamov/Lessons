package Functional.HW.task7;

import java.util.List;
import java.util.stream.Stream;

//Плоский список
//Из List<List<Integer>> сделай отсортированный List<Integer> без дубликатов.
// Используй flatMap, distinct и sorted.
public class SortedUniqueList {
    public static void main(String[] args) {
        List<List<Integer>> lists = List.of(List.of(1, 2, 3, 4, 5),
                List.of(1, 2, 3, 3, 2),
                List.of(6, 7, 7, 1, 2, 4));
        getSortedUniqueList(lists);

    }

    public static void getSortedUniqueList(List<List<Integer>> list) {
        list.stream()
                .flatMap(List::stream)
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }
}
