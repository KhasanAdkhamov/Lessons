package work_16_01_JAVA_CONCEPTS.hw.task2;

public class EmailTest {
    public static void main(String[] args) {
        Email email = new Email("hasan.adxamov@gmail.com");
        System.out.println(email.toString());
        Email email2 = new Email("hasan.adxamov@gmail.com");
        System.out.println(email.equals(email2));
    }
}
