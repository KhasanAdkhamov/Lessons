package work_07_01_26;

public class Animal {

    protected String name;
    protected int age;

    public Animal(String name, int age){
        this.name = name;
        this.age = age;
        System.out.println("Создан Animal");
    }

    public void sleep(){
        System.out.println("Животное спит");
    }

    public void eat(){
        System.out.println("Животное ест");
    }

    public void makeSound(){
        System.out.println("Животное издает звук");
    }
}
