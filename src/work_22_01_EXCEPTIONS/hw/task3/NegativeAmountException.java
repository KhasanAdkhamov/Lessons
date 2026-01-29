package work_22_01_EXCEPTIONS.hw.task3;

public class NegativeAmountException extends NullPointerException {
    public NegativeAmountException(String message) {
        super(message);
    }
}
