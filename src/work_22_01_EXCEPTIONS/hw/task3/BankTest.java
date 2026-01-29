package work_22_01_EXCEPTIONS.hw.task3;

public class BankTest {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount("Anton", 100);
        try {
            System.out.println(bankAccount.transferMoney(10000));
        } catch (AccountBlockedException e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println(bankAccount.buySmt(20000));
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }
}
