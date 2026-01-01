package lessonTest.samples.email;

import java.util.regex.Pattern;

public class Email {
    private String email;
    private String regex = "^\\w+@\\w+.\\w+.\\w+$";

    public Email(String email) {
        setEmail(email);
    }

    public void setEmail(String email) {
        if (email.isEmpty() || !Pattern.matches(regex, email)) {
            throw new NullPointerException("enter you email");
        }
        this.email = email;
    }


    public String getEmail() {
        return email;
    }
}
