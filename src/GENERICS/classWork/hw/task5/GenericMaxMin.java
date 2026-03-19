package GENERICS.classWork.hw.task5;

import java.util.List;

public class GenericMaxMin {

    public static <T extends Comparable<T>> T max(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        T max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            T current = list.get(i);
            if (current.compareTo(max) > 0) {
                max = current;
            }
        }
        return max;
    }

    public static <T extends Comparable<T>> T min(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        T min = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            T current = list.get(i);
            if (current.compareTo(min) < 0) {
                min = current;
            }
        }
        return min;
    }
}
