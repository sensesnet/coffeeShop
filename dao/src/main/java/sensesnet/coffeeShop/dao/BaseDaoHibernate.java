package sensesnet.coffeeShop.dao;

import sensesnet.coffeeShop.daoExceptions.DaoException;
import sensesnet.coffeeShop.daoUtils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

/**
 * Base class for IDaoHibernate, use hibernate
 * operate add/get/load/delete and getList of entitys from DB
 *
 * @param <T>
 */
public class BaseDaoHibernate<T> implements IDaoHibernate<T> {

    private static Logger logger = Logger.getLogger(BaseDaoHibernate.class);
    private static HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
    private static Session session = null;

    /**
     * add entity to DB
     *
     * @param t
     * @throws SQLException
     * @throws DaoException
     */
    public void add(T t) throws SQLException, DaoException {
        try {
            session = hibernateUtil.getSession();
            session.save(t);
        } catch (HibernateException e) {
            logger.error("Save object error" + t, e);
            throw new DaoException("Save object error", e);
        }
    }

    /**
     * remove entity from DB
     *
     * @param t
     * @throws SQLException
     * @throws DaoException
     */
    public void remove(T t) throws SQLException, DaoException {
        try {
            session = hibernateUtil.getSession();
            session.delete(t);
        } catch (HibernateException e) {
            logger.error(" - Error remove object", e);
            throw new DaoException("Error remove object", e);
        }
    }

    /**
     * get by ID entity from DB
     *
     * @param id
     * @return
     * @throws SQLException
     * @throws DaoException
     */
    public T getById(Serializable id) throws SQLException, DaoException {
        T t = null;
        try {
            session = hibernateUtil.getSession();
            String cl = getPersistentClass().getSimpleName();
            t = (T) session.load(getPersistentClass(), id);
        } catch (HibernateException e) {
            logger.error(" - Error get by id object", e);
            throw new DaoException(e);
        }
        return t;
    }

    /**
     *  get all entities from DB
     *
      * @return
     * @throws SQLException
     * @throws DaoException
     */
    public List<T> getAll() throws SQLException, DaoException {

        List<T> list = null;
        String cl = getPersistentClass().getSimpleName();
        String hql = "FROM " + getPersistentClass().getSimpleName();
        try {
            session = hibernateUtil.getSession();
            Query query = session.createQuery(hql);
            list = query.list();
        } catch (HibernateException e) {
            logger.error("- Error get all", e);
            throw new DaoException(e);
        }
        return list;
    }

    /**
     * edit entity from DB
     *
     * @param t
     * @throws SQLException
     * @throws DaoException
     */
    public void edit(T t) throws SQLException, DaoException {
        try {
            session = hibernateUtil.getSession();
            session.saveOrUpdate(t);
            logger.info("Object was updated" + t);
        } catch (HibernateException e) {
            logger.error("Error edit/update object" + t, e);
            throw new DaoException(e);
        }
    }

    /**
     * method return persistent class
     *
     * @return
     */
    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * method close session
     *
     */
    public void closeSession() {
        hibernateUtil.closeSession(hibernateUtil.getSession());
    }

}
