package work_07_01_26.homework.task8;

public class BuyOneGetOneFree extends Discount{


    public BuyOneGetOneFree(String code, String description) {
        super(code, description);
    }

    @Override
    public double apply(double price) {
        return 0;
    }
}
