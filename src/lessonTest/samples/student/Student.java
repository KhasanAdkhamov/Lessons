package lessonTest.samples.student;

public class Student {
    private String name;
    private int age;
    private double averageGrade;

    public Student(String name, int age, double averageGrade) {
        setName(name);
        setAge(age);
        setAverageGrade(averageGrade);
    }

    public void displayInfo() {
        System.out.println(toString());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.isEmpty()) {
            throw new NullPointerException("name must not empty or null");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age < 16 || age > 100) {
            throw new RuntimeException("age from 16 to 100");
        }
        this.age = age;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        if(averageGrade < 0.0 || averageGrade > 5.0) {
            throw new RuntimeException("don't have enough rewards or too much, it's fake");
        }
        this.averageGrade = averageGrade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", averageGrade=" + averageGrade +
                '}';
    }
}
