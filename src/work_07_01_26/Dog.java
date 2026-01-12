package work_07_01_26;

public class Dog extends Animal{
    public Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound(){
        System.out.println("Гав");
    }

}
