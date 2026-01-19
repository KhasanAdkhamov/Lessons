package work_07_01_26.homework.task8;

public class PercentDiscount extends Discount{
    private int percent;

    public PercentDiscount(String code, String description, int percent) {
        super(code, description);
        this.percent = percent;
    }

    @Override
    public double apply(double price) {
        return price * (1 - (double)percent/100);
    }
}
