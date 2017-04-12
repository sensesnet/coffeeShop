package sensesnet.coffeeShop.dao;

import sensesnet.coffeeShop.entity.CoffeeType;
import sensesnet.coffeeShop.daoExceptions.DaoException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class DaoCoffeeType extends BaseDaoHibernate<CoffeeType> {
    @Override
    public void add(CoffeeType coffeeType) throws SQLException, DaoException {
        super.add(coffeeType);
    }

    @Override
    public void remove(CoffeeType coffeeType) throws SQLException, DaoException {
        super.remove(coffeeType);
    }

    @Override
    public CoffeeType getById(Serializable id) throws SQLException, DaoException {
        return super.getById(id);
    }

    @Override
    public List<CoffeeType> getAll() throws SQLException, DaoException {
        return super.getAll();
    }

    @Override
    public void edit(CoffeeType coffeeType) throws SQLException, DaoException {
        super.edit(coffeeType);
    }
}
