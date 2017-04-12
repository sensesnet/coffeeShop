package iServices;

import servicesException.ServicesException;
import java.util.List;

public interface IServices<T> {

    void add(T t) throws ServicesException;

    void remove(T t) throws ServicesException;

    T getById(long id) throws ServicesException;

    List<T> getAll() throws ServicesException;

    void edit(T t) throws ServicesException;

}
