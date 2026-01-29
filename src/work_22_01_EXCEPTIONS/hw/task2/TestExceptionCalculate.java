package work_22_01_EXCEPTIONS.hw.task2;

public class TestExceptionCalculate {
    public static void main(String[] args) {
        Calculate calculate = new Calculate(5, 0);
        try {
            System.out.println(calculate.div());
        } catch (CalculateException exception) {
            System.out.println(exception.getMessage());
        }
    }

}
