package dao.api;




import model.Entity;

import java.util.List;

public interface Dao<T extends Entity> {

    List<T> getAll();

    T getById(long key);

    T getBy(String fieldName, String value);

    List<T> getListBy(String fieldName, String value);

    void save(T entity);

    void delete(long key);

    void update(T entity);

}
