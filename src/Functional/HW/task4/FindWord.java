package Functional.HW.task4;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

//Самое длинное слово
//
//Найди самое длинное слово в списке строк. Используй Stream API и `Optional`.
public class FindWord {
    public static void main(String[] args) {
        getWord(List.of("Anton", "Anna", "Sergey"));
        getWord(List.of());

    }

    public static void getWord(List<String> list) {
         Optional<String> word = list.stream()
                .max(Comparator.comparingInt(String::length));
         word.ifPresentOrElse(System.out::println, () -> System.out.println("список пуст") );





    }
}
