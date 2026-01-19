package work_16_01_JAVA_CONCEPTS;

// Неизменяемый класс для денег (сумма + валюта). Методы: add(), subtract() — возвращают новый объект.

import java.util.Objects;

public class Money {
    private final int totalSum;
    private final String currency;

    public Money(int totalSum, String currency) {
        this.totalSum = totalSum;
        this.currency = currency;
    }

    public Money add(Money money) {
        if (!this.currency.equals(money.currency)) {
            throw new RuntimeException("");
        }
        return new Money(this.totalSum + money.getTotalSum(), this.currency);
    }

    public int getTotalSum() {
        return totalSum;
    }

    public String getCurrency() {
        return currency;
    }

    public Money subtract(Money money) {
        if (!this.currency.equals(money.getCurrency())) {
            throw new RuntimeException("");
        }
        return new Money(this.totalSum - money.getTotalSum(), this.currency);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass() || this != o) return false;
        Money money = (Money) o;
        return totalSum == money.totalSum && Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalSum, currency);
    }

    @Override
    public String toString() {
        return String.format("сумма денег %d, валюта %s", totalSum,currency);
    }
}
