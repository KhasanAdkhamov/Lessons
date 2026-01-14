package work_07_01_26.homework.task8;

public abstract class Discount {
    protected String code;
    protected String description;
    protected boolean isActive;

    public Discount(String code, String description) {
        this.code = code;
        this.description = description;
        isActive = false;
    }

    public abstract double apply(double price);

    public boolean  activate() {
        if (!isActive) {
            System.out.println("скидка активирована " + description + " промокод " + code);
            isActive = true;
        }
        return false;
    }

    public boolean deactivate() {
        if (isActive) {
            System.out.println(" скидка деактивирована " + description);
            isActive = false;
        }
        return false;
    }

    public int getDiscountAmount(double price) {
        return 0;
    }
}
