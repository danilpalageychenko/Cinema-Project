package service.api;

import java.util.List;

public interface Service<T>{

    List<T> getAll();

    T getById(long id);

    void save(T entity);

    void delete(long key);

    void update(T entity);

}
