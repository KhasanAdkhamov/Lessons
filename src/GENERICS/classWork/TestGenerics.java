package GENERICS.classWork;

import java.util.ArrayList;
import java.util.List;

public class TestGenerics {
    public static void main(String[] args) {
        GenericTest<Integer> objectGenericTest = new GenericTest<>();
        List<Integer> list = new ArrayList<>();
        objectGenericTest.push(1, list);
        System.out.println(list);
        objectGenericTest.push(2, list);
        objectGenericTest.push(3, list);
        objectGenericTest.push(4, list);
        System.out.println(list);
        objectGenericTest.pop(list);
        System.out.println(list);
        System.out.println(objectGenericTest.peek(list, 1));
        System.out.println(objectGenericTest.isEmpty(list));
        objectGenericTest.push(5, list);
        //objectGenericTest.changePoints(list, 0, 2);
        System.out.println(list);
        objectGenericTest.changePoints(list, 0, 3);
    }
}
