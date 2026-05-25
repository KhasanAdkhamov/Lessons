package Functional.HW.task1;

import java.util.List;

//Фильтрация и преобразование
//
//Из списка чисел получи список квадратов чётных чисел.
//
//Входные данные: `List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)`. Ожидаемый результат: `[4, 16, 36, 64, 100]`.
public class FilterAndMap {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        list.stream()
                .filter(i-> i % 2==0)
                .map(i-> i * i)
                .forEach(System.out::println);

    }

}
