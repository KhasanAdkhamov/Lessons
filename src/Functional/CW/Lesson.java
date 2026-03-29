package Functional.CW;

import base.Functional;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Lesson {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ann", "Petr", "Vasya");
        List<String> filtered = new ArrayList<>();
        for (String name : names){
            if(name.length()>3){
                filtered.add(name.toUpperCase());
            }
        }
        Collections.sort(filtered);
        System.out.println(filtered);


        List<String> result = names.stream()
                .filter(name -> name.length()>3)
                .map(name -> name.toUpperCase())
                .sorted()
                .toList();
        System.out.println(result);


        Comparator<String> comp1 = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        };

        Comparator<String> comp2 = (s1, s2) -> s1.length()-s2.length();

        Runnable r  = () -> System.out.println("Hi");
        r.run();


        //=============

        MyFunc sq = x -> x*x-1;
        System.out.println(sq.apply(5));


        Predicate<String> isEmpty = String::isEmpty;
        System.out.println(isEmpty.test(""));

        Function<String, Integer> toLen = String::length;
        System.out.println(toLen.apply("Hello"));


        Function<String, Person> toPerson = Person::new;
        Person p = toPerson.apply("Petr");


        //==========

        List<String> names1 = List.of("a", "B");
        Stream<String> stream = names.stream();


        String[] names2 = {"a", "B"};
        Stream<String> stream2 = Arrays.stream(names2);

        Stream<String> stream3 = Stream.of("a","b");



    }
}
