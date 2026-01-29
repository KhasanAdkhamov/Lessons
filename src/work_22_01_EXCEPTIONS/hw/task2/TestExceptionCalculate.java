package work_22_01_EXCEPTIONS.hw.task2;

public class TestExceptionCalculate {
    public static void main(String[] args) {
        try {
            System.out.println(calculate(1, 0));
        } catch (CalculateException e) {
            System.out.println("некорректная операция - " + e.getMessage());
        }
    }

    public static int calculate(int x, int y) throws CalculateException {
        if (y == 0) {
            throw  new CalculateException("деление на ноль");
        }
        return x / y;
    }
}
