package Functional.HW.task10;

import java.util.List;

public class ParallelStream {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        System.out.println(getParallelStream(list));


    }

    public static List<Integer> getParallelStream(List<Integer> list) {
        return list.parallelStream()
                .map(n -> n * n)
                .toList();

    }
}
