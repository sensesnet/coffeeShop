import org.junit.Test;
import sensesnet.coffeeShop.entity.CoffeeType;
import sensesnet.coffeeShop.entity.OrderList;
import services.CoffeeTypeService;
import services.OrderListService;
import servicesException.ServicesException;

public class BaseServicesTest {

    @Test
    public void serviceTest() throws ServicesException {

        CoffeeTypeService coffeeTypeService = CoffeeTypeService.getCoffeeTypeService();
        CoffeeType coffeeType = new CoffeeType();
        coffeeType.setCoffeeName("Chibo_Brasil_US");
        coffeeType.setCoastPerHundredGrams(45.6);
        coffeeTypeService.add(coffeeType);

        OrderListService orderListService = OrderListService.getOrderListService();
        OrderList orderList = new OrderList();
        orderList.setAddress("WhiteSand st. 4/3");
        orderList.setDelivery(true);
        orderList.setDeliveryDate(null);
        orderList.setTotalPrice(12);
        orderList.setQuantity(500);
        orderList.setCoffeeType(coffeeType);
        orderListService.add(orderList);
    }
}
