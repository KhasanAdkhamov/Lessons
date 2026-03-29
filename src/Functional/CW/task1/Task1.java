package Functional.CW.task1;
//Задание 1: Фильтрация и преобразование (⭐)
//Из списка чисел получи список квадратов чётных чисел.

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Task1 {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8);

        List<Integer> stream = list.stream()
                .filter(number -> number % 2  == 0)
                .map(number -> number * number)
                .toList();
        System.out.println(stream);

    }
}
