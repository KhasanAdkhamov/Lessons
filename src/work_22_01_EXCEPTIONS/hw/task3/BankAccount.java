package work_22_01_EXCEPTIONS.hw.task3;

import java.util.Objects;

public class BankAccount {
    private String name;
    private int amountMoney;

    public BankAccount(String name, int amountMoney) {
        this.name = name;
        setAmountMoney(amountMoney);
    }

    public void setAmountMoney(int amountMoney) {
        if (amountMoney < 0) {
            throw new NegativeAmountException("сумма не может быть меньше 0");
        }
        this.amountMoney = amountMoney;
    }

    public int buySmt(int takeMoney) throws InsufficientFundsException {
        if (amountMoney < takeMoney) {
            throw new InsufficientFundsException("недостаточно средств");
        }
        return this.amountMoney - takeMoney;
    }

    public int transferMoney(int tMoney) throws AccountBlockedException {
        if (tMoney > 100000) {
            throw new AccountBlockedException("транзакция заблокирована");
        }
        return this.amountMoney + tMoney;
    }

    public String getName() {
        return name;
    }

    public int getAmountMoney() {
        return amountMoney;
    }



}
