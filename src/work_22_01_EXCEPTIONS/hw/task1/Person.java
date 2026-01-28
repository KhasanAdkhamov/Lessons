package work_22_01_EXCEPTIONS.hw.task1;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        try {
            setAge(age);
        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 150) {
            throw new InvalidAgeException("возраст может быть от 0 до 150");
        }
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("Person name: %s, age: %d", name, age);
    }
}
