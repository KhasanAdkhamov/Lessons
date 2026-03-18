package COLLECTIONS;

public class MyClassArrayList {
    private Object[] array;
    private int size;

    public MyClassArrayList() {
        array = new Object[10];
    }

    public void add(Object object) {
        resize(size+1);
        array[size] = object;
        size++;
    }

    private void resize(int minCapacity) {
        if(array.length >= minCapacity) {
            return;
        }
        Object[] newArray = new Object[(int) (array.length * 1.5)];
        // System.arraycopy(array, 0, newArray, 0, array.length);
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }
}
