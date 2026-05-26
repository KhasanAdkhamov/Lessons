package Functional.HW.task5;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Из текста (строки) получи Map<String, Long>, где ключ — слово в нижнем регистре,
// значение — сколько раз оно встречается. Разделяй текст на слова по пробелам и знакам пунктуации.
public class CountWords {
    public static void main(String[] args) {
        String text = "Hello, world, java, text! I am writing new TEXT for you now in Java!";
        System.out.println(getMap(text));
    }

    public static Map<String, Long> getMap(String text) {
        return Pattern.compile("[\\p{P}\\s]+")
                .splitAsStream(text)
                .filter(w-> !w.isEmpty())
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    }
}
