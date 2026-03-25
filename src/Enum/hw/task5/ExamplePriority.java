package Enum.hw.task5;

public class ExamplePriority {
    public static void main(String[] args) {
        System.out.println(Priority.LOW.isHigherThan(Priority.MEDIUM));
        System.out.println(Priority.fromLevel(1));
        Priority priority = Priority.fromLevel(4);
        System.out.println(priority);

    }
}
