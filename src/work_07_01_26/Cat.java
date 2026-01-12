package work_07_01_26;

public class Cat extends Animal{
    private boolean isInDoor;


    public Cat(String name, int age, boolean isInDoor){
        super(name, age);
        this.isInDoor = isInDoor;

        System.out.println("Создана Кошка");
    }

    @Override
    public void makeSound(){
        System.out.println("Мау");
    }

}
