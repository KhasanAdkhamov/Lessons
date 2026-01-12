package work_27_12_25;

public class User {

    private String name;
    private String password;
    private boolean active;

    public User(String name) {
        this.name = name;
    }

    public User(String name, String password) {
        this.name = name;
        setPassword(password);
    }

    public void registerInDataBase(){
        DataBaseSimulator.register(this);
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public void setPassword(String password) {
        if(password.length()<8){
            throw new RuntimeException("пароль должен быть длинее 8 символов");
        }
        this.password = password;
    }


}
