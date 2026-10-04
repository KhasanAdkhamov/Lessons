package Files.hw.task10;

public class Time {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(2000);
        long end = System.currentTimeMillis();
        System.out.println(start);
        System.out.println(end);
    }
}
