package work_27_12_25;

public class Lesson {

    public int number;
    protected int number2;
    private int number3;
    int number4;

    private static final String DEFAULT_NAME = "Ann";

    public static void main(String[] args) {
//        Cat cat = new Cat();
//        cat.setName("Barsik");
//        cat.setAge(10);

        Cat cat2 = new Cat("Barsik",10);
        cat2.setAge(20);
        Cat cat = new Cat("tom", 21);
    }


    public void sum(int a, int b){
        int result = a+b;
        System.out.println(Util.DEFAULT_NAME);
        lPrint(String.valueOf(result));
    }

    private void lPrint(String msg) {
        System.out.println(msg);
    }

    private void print1() {
        System.out.println(DEFAULT_NAME);
    }

    private void print2() {
        System.out.println(DEFAULT_NAME);
    }

    private void print3() {
        System.out.println(DEFAULT_NAME);
    }

    private void print4() {
        System.out.println(DEFAULT_NAME);
    }

    private void print5() {
        System.out.println(DEFAULT_NAME);
    }
}
