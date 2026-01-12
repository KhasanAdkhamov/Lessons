package lessonTest.samples.person;

public class PersonTest {
    public static void main(String[] args) {
        Person person = new Person("anton", 1999, 12, 21);
        System.out.println(person.getAge());
        System.out.println(person.isAdult());
        System.out.println(person);
        System.out.println(person.getBirthDay());
        System.out.println(person.getBirthMonth());
        System.out.println(person.getBirthYear());
        System.out.println(person.getBirthDate());
        System.out.println(person.isMinor());
    }
}
