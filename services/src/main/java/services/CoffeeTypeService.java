package services;

import iServices.IServices;
import org.hibernate.Transaction;
import sensesnet.coffeeShop.dao.DaoCoffeeType;
import sensesnet.coffeeShop.dao.DaoFactory;
import sensesnet.coffeeShop.daoUtils.HibernateUtil;
import sensesnet.coffeeShop.entity.CoffeeType;
import servicesException.ServicesException;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class CoffeeTypeService implements IServices<CoffeeType> {

    private static Logger logger = Logger.getLogger(CoffeeTypeService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private DaoCoffeeType daoCoffeeType = daoFactory.getDaoCoffeeType();
    private static CoffeeTypeService coffeeTypeService;

    public static CoffeeTypeService getCoffeeTypeService() {
        if (coffeeTypeService == null) {
            coffeeTypeService = new CoffeeTypeService();
        }
        return coffeeTypeService;
    }

    public void add(CoffeeType coffeeType) throws ServicesException {
        Transaction transaction = null;
        try {
            transaction = HibernateUtil.getHibernateUtil().getSession().beginTransaction();
            daoCoffeeType.add(coffeeType);
            logger.info(" - New CoffeeType object was add: " + coffeeType);
            transaction.commit();
        } catch (Exception e) {
            logger.error(" - New CoffeeType object wasn't add: " + e);
            if (transaction != null) transaction.rollback();
        }
    }

    public void remove(CoffeeType coffeeType) throws ServicesException {
        Transaction transaction = null;
        try {
            transaction = HibernateUtil.getHibernateUtil().getSession().beginTransaction();
            daoCoffeeType.remove(coffeeType);
            logger.info(" - Object CoffeeType removed: " + coffeeType);
            transaction.commit();
        } catch (Exception e) {
            logger.error(" - Object CoffeeType wasn't remove: " + e);
            if (transaction != null) transaction.rollback();
        }
    }

    public CoffeeType getById(long id) throws ServicesException {
        Transaction transaction = null;
        CoffeeType coffeeType = null;
        try {
            transaction = HibernateUtil.getHibernateUtil().getSession().beginTransaction();
            coffeeType = daoCoffeeType.getById(id);
            logger.info(" - Object CoffeeType was get by ID: " + coffeeType);
            transaction.commit();
        } catch (Exception e) {
            logger.error(" - Object CoffeeType wasn't get by ID: " + e);
            if (transaction != null) transaction.rollback();
        }
        return coffeeType;
    }

    public List<CoffeeType> getAll() throws ServicesException {
        Transaction transaction = null;
        List<CoffeeType> list = new ArrayList<>();
        try {
            transaction = HibernateUtil.getHibernateUtil().getSession().beginTransaction();
            list = daoCoffeeType.getAll();
            logger.info(" - Objects CoffeeType was found!");
            transaction.commit();
        } catch (Exception e) {
            logger.error(" - Objects CoffeeType was found: " + e);
            if (transaction != null) transaction.rollback();
        }
        return list;
    }

    public void edit(CoffeeType coffeeType) throws ServicesException {
        Transaction transaction = null;
        try {
            transaction = HibernateUtil.getHibernateUtil().getSession().beginTransaction();
            daoCoffeeType.edit(coffeeType);
            logger.info(" - Object CoffeeType was edit: " + coffeeType);
            transaction.commit();
        } catch (Exception e) {
            logger.error(" - Object CoffeeType wasn't edit" + e);
            if (transaction != null) transaction.rollback();
        }
    }
}
