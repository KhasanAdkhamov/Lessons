package GENERICS.classWork;

import java.util.List;

public class Box<T> {
    private T content;


    public void put(T item){
        this.content = item;
    }

    public T get(){
        return this.content;
    }

}
