package work_16_01_JAVA_CONCEPTS.hw.task2;
//Неизменяемый класс для email-адреса с валидацией в конструкторе.
import java.util.regex.Pattern;

public final class Email {
    private final String address;
    private String regex = "^.+@\\w+\\.\\w+$";


    public Email(String address) {
        this.address = setAddress(address);
    }

    public String setAddress(String address) {
        if (address.isEmpty() || !Pattern.matches(regex, address)) {
            throw new NullPointerException("");
        }
        return address;
    }

    public String getAddress() {
        return address;
    }
}
