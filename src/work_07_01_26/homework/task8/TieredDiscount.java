package work_07_01_26.homework.task8;

public class TieredDiscount extends Discount{
    private double[] thresholds;
    private int[] discounts;

    public TieredDiscount(String code, String description) {
        super(code, description);
    }

    @Override
    public double apply(double price) {
        return 0;
    }
}
