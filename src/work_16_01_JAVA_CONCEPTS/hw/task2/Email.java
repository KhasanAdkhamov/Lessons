package work_16_01_JAVA_CONCEPTS.hw.task2;
//Неизменяемый класс для email-адреса с валидацией в конструкторе.
import java.util.Objects;
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

    @Override
    public String toString() {
        return String.format("Email: " +
                "address %s", address);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;

        Email other = (Email) o;

        return Objects.equals(address, other.address);
    }

    @Override
    public int hashCode() {
        int result = 19;

        result = 7 * result + (address != null?address.hashCode():0);
        return result;
    }
}
