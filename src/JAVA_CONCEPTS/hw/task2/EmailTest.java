package JAVA_CONCEPTS.hw.task2;

public class EmailTest {
    public static void main(String[] args) {
        Email email = new Email("hasan.adxamov@gmail.com");
        Email email3 = new Email("hasan.adxamov@gmail.com");
        System.out.println(email.toString());
        Email email2 = new Email("hasan.adxamov@gmail.com");
        System.out.println(email.equals(email2));
        System.out.println(email2.setAddress("fmfmf.sf@mail.com"));
        System.out.println(email2.equals(email));
        System.out.println(email2);
        System.out.println(email);
        System.out.println(email3);
    }
}
