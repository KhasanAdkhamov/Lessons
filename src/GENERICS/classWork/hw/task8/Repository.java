package GENERICS.classWork.hw.task8;

import java.util.Map;

public interface Repository<T, ID> {

    ID findById(T type);

    Map<T, ID> findAll();

    void save(T type, ID id);

    void delete(T type);
}
