package work_07_01_26.homework.task8;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Double> items;
    private double discountAmount;

    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.discountAmount = 0;
    }

    public void addItem(double price) {
        if (price < 0) {
            throw new NullPointerException("цена не может быть нулевой");
        }
        items.add(price);
    }

    public double getSubtotal() {
        double totalSum = 0;
        for (Double item : items) {
            totalSum += item;
        }
        return totalSum;
    }

    public double applyDiscount(Discount discount) {
        double subtotal = getSubtotal();
        return discount.apply(subtotal);
    }

    public void applyBestDiscount(Discount[] discounts) {
        double subtotal = getSubtotal();
        double bestDiscount = 0;
        for (Discount discount : discounts) {
            double currentDiscount = discount.apply(subtotal);
            if (currentDiscount > bestDiscount) {
                bestDiscount = currentDiscount;
            }
        }
        this.discountAmount = bestDiscount;
        System.out.println(discountAmount);
    }

    public double calculateTotal(){
        double subtotal = getSubtotal();
        return Math.max(0, subtotal - discountAmount);
    }
}
