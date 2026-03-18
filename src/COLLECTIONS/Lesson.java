package COLLECTIONS;

import java.util.*;

public class Lesson {

    public static void main(String[] args) {
        String[] arrNames = new String[2]; // [null, null]
        arrNames[0] = "Ann";
        arrNames[1] = "Ann2";
//        arrNames.length


        List<String> listName = new ArrayList<>(); // []
        listName.add("1");
        listName.add("2");
        listName.add("3");
        listName.add("4");
        listName.add("5");
//        listName.size()


//        for (String name : listName){
//            if(name.equals("2")){
//                listName.remove(name);
//            }
//        }

        Iterator<String> it = listName.iterator();
        while (it.hasNext()){
            String name = it.next();
            if(name.equals("2")){
                it.remove();
            }
        }
        System.out.println(listName);


        Set<String> setNames = new HashSet<>();
        setNames.add("1");
        setNames.add("2");
        setNames.add("1");

        System.out.println(setNames.size());

        Set<Integer> setNumber = new TreeSet<>();
        setNumber.add(30);
        setNumber.add(10);
        setNumber.add(20);

        System.out.println(setNumber);

    }

    public static void removeElem() {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(1);
        list.add(7);
        list.add(3);

        Set<Integer> set = new LinkedHashSet<>(list);
        List<Integer> integerList = new ArrayList<>(set);
    }


}
