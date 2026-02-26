package GENERICS.classWork;

import java.util.List;

public class Util {

    public static <T> T getFirst(List<T> list){
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

}
