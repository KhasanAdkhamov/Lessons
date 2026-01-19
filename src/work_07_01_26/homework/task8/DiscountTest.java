package work_07_01_26.homework.task8;

import lessonTest.samples.person.Person;

public class DiscountTest {
    public static void main(String[] args) {
        Discount buyOneGetOneFree = new BuyOneGetOneFree("bogof", "купи 2=1");
        Discount percentDiscount = new PercentDiscount("pd", "процент скидки", 10);
        Discount fixedDiscount = new FixedDiscount("fd", "фикс скидка", 200);
        Discount tieredDiscount = new TieredDiscount("td", "зависит от суммы");
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(500);
        shoppingCart.addItem(2500);
        shoppingCart.addItem(5000);
        System.out.println(shoppingCart.getSubtotal());
        System.out.println(shoppingCart.applyDiscount(buyOneGetOneFree));
        System.out.println(shoppingCart.applyDiscount(percentDiscount));
        System.out.println(shoppingCart.applyDiscount(fixedDiscount));
        System.out.println(shoppingCart.applyDiscount(tieredDiscount));
        System.out.println(tieredDiscount.apply(2000));
        Discount[] discounts = new Discount[]{buyOneGetOneFree, percentDiscount, fixedDiscount, tieredDiscount};
        System.out.println(percentDiscount.apply(100));
        shoppingCart.applyBestDiscount(discounts);
    }
}
