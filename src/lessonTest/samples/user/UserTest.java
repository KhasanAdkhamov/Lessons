package lessonTest.samples.user;

public class UserTest {
    public static void main(String[] args) {
        User user = new User("anton", "sfdsfd");
        System.out.println(user.isActive());
        System.out.println(user.checkPassword("sfdsfd"));
    }
}
