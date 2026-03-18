package GENERICS.classWork.hw;

import java.util.HashMap;
import java.util.Map;

public class Cache <K, V> {
    private Map<K,V> map;
    private int size = 0;

    public Cache() {
        map = new HashMap<>();
    }

    public void put(K key, V value) {
       map.put(key, value);
       size++;
    }

    public V get(K key) {
        return map.get(key);
    }

    public boolean containsKey(K key) {
        if (map == null || map.isEmpty()) {
            return false;
        }
        for (K k : map.keySet()) {
            if (k == key) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void clear() {
        map.clear();
    }

}
