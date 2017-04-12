
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.junit.Test;
import sensesnet.coffeeShop.dao.DaoCoffeeType;
import sensesnet.coffeeShop.dao.DaoFactory;
import sensesnet.coffeeShop.dao.DaoOrderList;
import sensesnet.coffeeShop.daoExceptions.DaoException;
import sensesnet.coffeeShop.daoUtils.HibernateUtil;
import sensesnet.coffeeShop.entity.CoffeeType;
import sensesnet.coffeeShop.entity.OrderList;
import java.util.Date;

import java.sql.SQLException;


public class BaseDaoTest {

    private static Logger logger = Logger.getLogger(BaseDaoTest.class);
    @Test
    public void addTest() throws SQLException, DaoException {
        logger.info("Dao test started!");
        DaoFactory daoFactory = DaoFactory.getInstance();
        DaoCoffeeType daoCoffeeType = daoFactory.getDaoCoffeeType();
        DaoOrderList daoOrderList = daoFactory.getDaoOrderList();

        //open session and start tranzaction
        HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
        Session session = hibernateUtil.getSession();
        session.beginTransaction();


        CoffeeType coffeeType = new CoffeeType();
        coffeeType.setId(1);
        coffeeType.setCoffeeName("Jacobs_Porto_EU");
        coffeeType.setCoastPerHundredGrams(98.3);
        daoCoffeeType.add(coffeeType);

        CoffeeType coffeeType2 = new CoffeeType();
        coffeeType2.setId(2);
        coffeeType2.setCoffeeName("Jockey_Moskow_RU");
        coffeeType2.setCoastPerHundredGrams(50.2);
        daoCoffeeType.add(coffeeType2);


        OrderList orderList = new OrderList();
        orderList.setAddress("Briz st. 7/5");
        orderList.setDelivery(true);
        orderList.setQuantity(200);
        orderList.setTotalPrice(67.4);
        orderList.setDeliveryDate(new Date());
        orderList.setCoffeeType(coffeeType);
        daoOrderList.add(orderList);

        OrderList orderList2 = new OrderList();
        orderList2.setAddress("Duna st. 8/3");
        orderList2.setDelivery(true);
        orderList2.setQuantity(200);
        orderList2.setDeliveryDate(new Date());
        orderList2.setTotalPrice(67.4);
        orderList2.setCoffeeType(coffeeType2);
        daoOrderList.add(orderList2);


        session.save(coffeeType);
        session.save(coffeeType2);
        session.save(orderList);
        session.save(orderList2);
        session.getTransaction().commit();
        hibernateUtil.closeSession(session);

        logger.info("Dao test ended!");

    }
}
