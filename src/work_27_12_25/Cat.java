package work_27_12_25;

public class Cat {

    private final String name;
    private int age;

    // 1 вариант
    public Cat(String name, int age) {
        this(name);
        this.age = age;
    }


    public Cat(String name) {
        this.name = name;
    }

    public void setAge(int age) {

        this.age = age;
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
