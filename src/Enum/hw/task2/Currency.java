package Enum.hw.task2;

public enum Currency {
    USD("usd", "$", "dollar"),
    RUB("rub", "РУБ", "рубль"),
    SUM("sum", "S", "som"),
    EU("eu", "Э", "euro");

    private final String code;
    private final String symbol;
    private final String name;

    Currency(String code, String symbol, String name) {
        this.code = code;
        this.symbol = symbol;
        this.name = name;
    }

    public String format(double amount) {
        return amount + " " + this.symbol;
    }
}
