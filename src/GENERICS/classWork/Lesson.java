package GENERICS.classWork;

import base.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Lesson {

    public static void main(String[] args) {

        Box<String> strBox = new Box<>();
        strBox.put("Hi");
        String str = strBox.get();


        Box<Integer> intox = new Box<>();
        intox.put(3);
        Integer ints = intox.get();


        List<String> names = List.of("Ann", "Petr");
        String first = Util.getFirst(names);

//
//        List<Dog> dogs = new ArrayList<>();
//        List<Animal> animals = dogs;
//        animals.add(new Cat());
//        Dog dog = animals.get(0);


//        printAll(List.of(1,3,4));

        printAll(List.of(new Dog(), new Cat()));
    }


    public static void printAll(List<? extends Animal> list){
        for(Object o : list){
            System.out.println(o);
        }
//        list.add(new Dog());
    }


    public static void printAll2(List<? super Dog> list){
        for(Object o : list){
            System.out.println(o);
        }

        list.add(new Dog());
    }


    public static void printAll3(List<Dog> list){
        for(Object o : list){
            System.out.println(o);
        }

        list.add(new Dog());
    }
}
