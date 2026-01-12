package work_07_01_26;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Lesson {


    public static void main(String[] args) {
        Animal cat = new Cat("Barsik", 11, true);
        Animal dog = new Dog("Sharik", 1);

        bisLogic(cat);
        bisLogic(dog);


        List<String> strings = new LinkedList<>();

//        Calc calc = new Calc();
//        calc.printSum(1,2,3);
//        calc.printSum(1,2);


    }

    public static void bisLogic(Animal animal){
        animal.makeSound();
    }


}
