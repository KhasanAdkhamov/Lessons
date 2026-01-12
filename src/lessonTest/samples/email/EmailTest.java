package lessonTest.samples.email;

import java.util.regex.Pattern;

public class EmailTest {
    public static void main(String[] args) {
        Email email = new Email("hjbkjb@sad.com");
        System.out.println(email.getDomain());
        System.out.println(email.getUsername());
        //String regex = "^\\w+@\\w+.\\w+.\\w+$";
        //System.out.println(Pattern.matches(regex, "hasandsd@gmail.com"));
    }
}
