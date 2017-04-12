package services;

import iServices.IServices;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import sensesnet.coffeeShop.dao.DaoFactory;
import sensesnet.coffeeShop.dao.DaoOrderList;
import sensesnet.coffeeShop.daoUtils.HibernateUtil;
import sensesnet.coffeeShop.entity.OrderList;
import servicesException.ServicesException;

import java.util.ArrayList;
import java.util.List;

public class OrderListService implements IServices<OrderList> {

    private static Logger logger = Logger.getLogger(OrderListService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private DaoOrderList daoOrderList = daoFactory.getDaoOrderList();
    private static OrderListService orderListService;

    public static OrderListService getOrderListService() {
        if (orderListService == null) {
            orderListService = new OrderListService();
        }
        return orderListService;
    }

    public void add(OrderList orderList) throws ServicesException {
        Transaction transaction = null;
        try {
            transaction = HibernateUtil.getHibernateUtil().getSession().beginTransaction();
            daoOrderList.add(orderList);
            logger.info(" - New OrderList object was add: " + orderList);
            transaction.commit();
        } catch (Exception e) {
            logger.error(" - New CoffeeType object wasn't add: " + e);
            if (transaction != null) transaction.rollback();
        }
    }

    public void remove(OrderList orderList) throws ServicesException {
        Transaction transaction = null;
        try {
            transaction = HibernateUtil.getHibernateUtil().getSession().beginTransaction();
            daoOrderList.remove(orderList);
            logger.info(" - Object OrderList removed: " + orderList);
            transaction.commit();
        } catch (Exception e) {
            logger.error(" - Object OrderList wasn't remove: " + e);
            if (transaction != null) transaction.rollback();
        }
    }

    public OrderList getById(long id) throws ServicesException {
        Transaction transaction = null;
        OrderList orderList = null;
        try {
            transaction = HibernateUtil.getHibernateUtil().getSession().beginTransaction();
            orderList = daoOrderList.getById(id);
            logger.info(" - Object OrderList was get by ID: " + orderList);
            transaction.commit();
        } catch (Exception e) {
            logger.error(" - Object OrderList wasn't get by ID: " + e);
            if (transaction != null) transaction.rollback();
        }
        return orderList;
    }

    public List<OrderList> getAll() throws ServicesException {
        Transaction transaction = null;
        List<OrderList> list = new ArrayList<>();
        try {
            transaction = HibernateUtil.getHibernateUtil().getSession().beginTransaction();
            list = daoOrderList.getAll();
            logger.info(" - Objects OrderList was found!");
            transaction.commit();
        } catch (Exception e) {
            logger.error(" - Objects OrderList was found: " + e);
            if (transaction != null) transaction.rollback();
        }
        return list;
    }

    public void edit(OrderList orderList) throws ServicesException {
        Transaction transaction = null;
        try {
            transaction = HibernateUtil.getHibernateUtil().getSession().beginTransaction();
            daoOrderList.edit(orderList);
            logger.info(" - Object OrderList was edit: " + orderList);
            transaction.commit();
        } catch (Exception e) {
            logger.error(" - Object OrderList wasn't edit" + e);
            if (transaction != null) transaction.rollback();
        }
    }
}
