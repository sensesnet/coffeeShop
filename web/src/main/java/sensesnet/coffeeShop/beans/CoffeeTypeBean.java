package sensesnet.coffeeShop.beans;

import sensesnet.coffeeShop.entity.CoffeeType;
import sensesnet.coffeeShop.entity.OrderList;
import services.CoffeeTypeService;
import services.OrderListService;
import servicesException.ServicesException;

import javax.el.MethodExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "coffeeTypeBean")
@SessionScoped
public class CoffeeTypeBean {

    private long id;
    private String coffeeName;
    private double coastPerHundredGrams;
    private CoffeeTypeService coffeeTypeService = new CoffeeTypeService();
    private OrderListService orderListService = new OrderListService();

    //
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public double getCoastPerHundredGrams() {
        return coastPerHundredGrams;
    }

    public void setCoastPerHundredGrams(double coastPerHundredGrams) {
        this.coastPerHundredGrams = coastPerHundredGrams;
    }

    public List<CoffeeTypeBean> getGetAllCoffeeType() {
        List<CoffeeType> array;
        List<CoffeeTypeBean> arrayBean = new ArrayList<>();

        try {
            array = coffeeTypeService.getAll();
            for (CoffeeType ct : array) {
                CoffeeTypeBean ctb = new CoffeeTypeBean();
                ctb.setId(ct.getId());
                ctb.setCoffeeName(ct.getCoffeeName());
                ctb.setCoastPerHundredGrams(ct.getCoastPerHundredGrams());
                arrayBean.add(ctb);
            }
        } catch (ServicesException e) {
            e.printStackTrace();
        }
        return arrayBean;
    }

    public List<OrderList> getGetAllCoffeeOrders() {
        List<OrderList> orderList = null;
        try {
            orderList = orderListService.getAll();
        } catch (ServicesException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    public List<String> complete() {
        List<CoffeeType> coffeeTypes = null;
        List<String> results = new ArrayList<>();
        try {
            coffeeTypes = coffeeTypeService.getAll();
            for (CoffeeType list : coffeeTypes) {
                results.add(list.getCoffeeName());
            }
        } catch (ServicesException e) {
            e.printStackTrace();
        }
        return results;
    }
}
