package work_22_01_EXCEPTIONS.hw.task2;

public class Calculate{
   private int x;
   private int y;

   public Calculate(int x, int y) {
       this.x = x;
       this.y = y;
   }

   public int div() throws CalculateException {
       if (y == 0) {
           throw new CalculateException("деление на ноль, некорректная операция");
       }
       return x / y;
   }
}
