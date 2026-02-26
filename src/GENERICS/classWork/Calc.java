package GENERICS.classWork;

public class Calc<T extends Number & Comparable<T>> {

    public double add(T a, T b){
        return a.doubleValue()+b.doubleValue();
    }


    public static <T extends Comparable<T>> T max(T a, T b){
        return a.compareTo(b) > 0? a:b;
    }

}
