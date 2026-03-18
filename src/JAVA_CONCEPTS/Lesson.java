package JAVA_CONCEPTS;

public class Lesson {

    public static void main(String[] args) {
        Person person = new Person("Ann", 12,"1234");

        Person person2 = new Person("Ann", 12,"1234");

        System.out.println(person == person2);

        System.out.println(person.equals(person2));


        System.out.println(person);

    }

    // Неизменяемый класс для денег (сумма + валюта). Методы: add(), subtract() — возвращают новый объект.

}
