package GENERICS.classWork.hw.task7;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class TransformGenerics {

    public static <T, R> List<R> transform(List<T> list, Function<T, R> mapper) {
        List<R> list2 = new ArrayList<>();
        for (T t : list) {
            R apply = mapper.apply(t);
            list2.add(apply);
        }
        return list2;
    }
}
