package GENERICS.classWork.hw.task8;

import java.util.HashMap;
import java.util.Map;

public class MapRepos<T, ID> implements Repository<T, ID>{

    private Map<T, ID> map;

    public MapRepos() {
        map = new HashMap<>();
    }

    @Override
    public ID findById(T type) {
        return map.get(type);
    }

    @Override
    public Map<T, ID> findAll() {
        Map map2 = new HashMap();
        if (map == null || map.isEmpty()) {
            return null;
        }
        for (Map.Entry<T, ID> entry : map.entrySet()) {
            System.out.println(entry);
        }
        return map2;
    }

    @Override
    public void save(T type, ID id) {
        map.put(type, id);
    }

    @Override
    public void delete(T type) {
        if (map == null || map.isEmpty()) {
            throw new NullPointerException("null");
        }
        for (T t : map.keySet()) {
            if (t.equals(type)) {
                map.remove(t);
            }
        }
    }
}
