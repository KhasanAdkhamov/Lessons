package hw_13_07_26.task1;

import java.util.*;
import java.util.stream.Collectors;

/*
водится число n Затем 2*n строк. Каждая пара строки имя человека и его профессия.
Определите, сотрудников какой профессии больше всего.
Выведите на экран это количество, профессию и их имена на экран в том же порядке, в котором они водились.
Гарантируется, что будет введено не более 100 сотрудников.
 */
public class Task1 {
    public static void main(String[] args) {
        List<Worker> workers = List.of(new Worker("иван", "токарь"),
                new Worker("семен", "программист"),
                new Worker("анатолий", "кузнец"),
                new Worker("вася", "финансист"),
                new Worker("кирилл", "программист"),
                new Worker("оля", "программист"),
                new Worker("валера", "токарь"));

        System.out.println(getInformation(workers));
    }

    public static Map<String, Long> getInformation(List<Worker> list) {
        Map<String, Long> map =list.stream()
                .collect(Collectors.groupingBy(Worker::getProfession, Collectors.counting()));
        return map;
    }

}
