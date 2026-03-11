package GENERICS.classWork;

import java.util.ArrayList;
import java.util.List;

public class GenericTest<T> {

    public void push(T item, List<T> list) {
        list.add(item);
    }

    public void pop(List<T> list) {
        if (list.isEmpty()) return;
        list.removeLast();
    }

    public boolean peek(List<T> list, T content) {
        if (list.isEmpty() || content == null) return false;
        for (T t : list){
            if (t.equals(content)) return true;
        }
        return false;
    }

    public boolean isEmpty(List<T> list) {
        if (list.isEmpty()) {
            return true;
        }
        return false;
    }

    public void changePoints(List<T> list, int x, int y) {
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
