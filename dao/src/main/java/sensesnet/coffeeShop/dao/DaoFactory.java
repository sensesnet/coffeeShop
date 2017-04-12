package sensesnet.coffeeShop.dao;

public class DaoFactory {

    private static DaoFactory instance = new DaoFactory();
    private DaoCoffeeType daoCoffeeType;
    private DaoOrderList daoOrderList;

    public static DaoFactory getInstance() {
        return DaoFactory.instance;
    }

    public DaoCoffeeType getDaoCoffeeType() {
        if (daoCoffeeType == null)
            daoCoffeeType = new DaoCoffeeType();
        return daoCoffeeType;
    }

    public DaoOrderList getDaoOrderList() {
        if (daoOrderList == null)
            daoOrderList = new DaoOrderList();
        return daoOrderList;
    }
}
