package GENERICS.classWork.hw;

import java.util.ArrayList;
import java.util.List;

public class TestGenerics {
    public static void main(String[] args) {
        GenericTest objectGenericTest = new GenericTest();
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
        List<Integer> list1 = List.of(1, 2, 5, 6, 8);
        System.out.println(GenericMaxMin.max(list1));
        System.out.println(GenericMaxMin.min(list1));
    }
}
