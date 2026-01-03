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

    public String getUsername() {
        String domain = "@";
        int index = email.indexOf(domain);
        if (index == -1) {
            throw new RuntimeException("don't have any username");
        }
        return email.substring(0, index-1);
    }

    public String getDomain() {
        String domain = "@";
        int index = email.indexOf(domain);
        if (index == -1) {
            throw new RuntimeException("don't have domain");
        }
        return email.substring(index +1);
    }

    public String getEmail() {
        return email;
    }
}
