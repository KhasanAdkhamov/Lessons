package lessonTest.samples.user;

public class User {
    private String username;
    private String password;
    private boolean isActive;

    public User(String username, String password) {
        this.username = username;
        setPassword(password);
        setActive(isActive);
    }

    public boolean checkPassword(String password) {
        if(this.password != password) {
            throw new NullPointerException("wrong password");
        }
        return true;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 6) {
            throw new NullPointerException("password must be more 6chars");
        }
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getUsername() {
        return username;
    }
}
