package work_07_01_26.homework.task8;

public class TieredDiscount extends Discount{
    private double[] thresholds;
    private int[] discounts;

    public TieredDiscount(String code, String description) {
        super(code, description);
        thresholds = new double[]{1000, 5000, 10000};
        discounts = new int[]{5, 10, 15};
    }

    @Override
    public double apply(double price) {
        double result = 0.0;
        if (price <= thresholds[0]) {
            result = price * (1 - (double)discounts[0] / 100);
        } else if (price <= thresholds[1]) {
            result = price * (1 - (double)discounts[1]/100);
        } else if (price <= thresholds[2]) {
            result = price * (1 - (double)discounts[2] / 100);
        }
        return result;
    }
}
