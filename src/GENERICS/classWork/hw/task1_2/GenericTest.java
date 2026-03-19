package GENERICS.classWork.hw.task1_2;

import java.util.ArrayList;
import java.util.List;

public class GenericTest {

    public <T> void push(T item, List<T> list) {
        list.add(item);
    }

    public <T> void pop(List<T> list) {
        if (list.isEmpty()) return;
        list.removeLast();
    }

    public<T> boolean peek(List<T> list, T content) {
        if (list.isEmpty() || content == null) return false;
        for (T t : list){
            if (t.equals(content)) return true;
        }
        return false;
    }

    public<T> boolean isEmpty(List<T> list) {
        if (list.isEmpty()) {
            return true;
        }
        return false;
    }

    public<T> void changePoints(List<T> list, int x, int y) {
        List<T> list2 = new ArrayList<>();
        if (list.isEmpty()) return;
        T first = list.get(x);
        T second = list.get(y);
        for (T t : list) {
            if (t == first || t == second) continue;
            list2.add(t);
        }
        list2.add(x, second);
        list2.add(y, first);
        System.out.println(list2);
    }

}
