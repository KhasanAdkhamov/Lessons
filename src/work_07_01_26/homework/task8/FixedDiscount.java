package work_07_01_26.homework.task8;

public class FixedDiscount extends Discount{
    private double amount;

    public FixedDiscount(String code, String description, double amount) {
        super(code, description);
        this.amount = amount;
    }

    @Override
    public double apply(double price) {
        return Math.max(0, price - amount);
    }
}
