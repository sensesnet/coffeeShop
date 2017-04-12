package sensesnet.coffeeShop.dao;

import sensesnet.coffeeShop.daoExceptions.DaoException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Base interface for Dao
 *
 * @param <T>
 */
public interface IDaoHibernate<T> {

    public void add(T t) throws SQLException, DaoException;

    public void remove(T t) throws SQLException, DaoException;

    public T getById(Serializable id) throws SQLException, DaoException;

    public List<T> getAll() throws SQLException, DaoException;

    public void edit(T t) throws SQLException, DaoException;

}
