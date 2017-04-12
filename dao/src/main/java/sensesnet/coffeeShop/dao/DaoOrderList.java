package sensesnet.coffeeShop.dao;

import sensesnet.coffeeShop.daoExceptions.DaoException;
import sensesnet.coffeeShop.entity.OrderList;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class DaoOrderList extends BaseDaoHibernate<OrderList> {
    @Override
    public void add(OrderList orderList) throws SQLException, DaoException {
        super.add(orderList);
    }

    @Override
    public void remove(OrderList orderList) throws SQLException, DaoException {
        super.remove(orderList);
    }

    @Override
    public OrderList getById(Serializable id) throws SQLException, DaoException {
        return super.getById(id);
    }

    @Override
    public List<OrderList> getAll() throws SQLException, DaoException {
        return super.getAll();
    }

    @Override
    public void edit(OrderList orderList) throws SQLException, DaoException {
        super.edit(orderList);
    }
}
