package sensesnet.coffeeShop.beans;

import org.apache.log4j.Logger;
import sensesnet.coffeeShop.entity.CoffeeType;
import sensesnet.coffeeShop.entity.OrderList;
import services.CoffeeTypeService;
import services.OrderListService;
import servicesException.ServicesException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "ordersListBean")
@SessionScoped
public class OrdersListBean {

    private OrderListService orderListService = new OrderListService();
    private CoffeeTypeService coffeeTypeService = new CoffeeTypeService();
    private static Logger logger = Logger.getLogger(OrdersListBean.class);

    private Long id;
    private double quantity;
    private String address;
    private Date deliveryDate;
    private boolean delivery;
    private double totalPrice;
    private String coffeeType;

    /**
     * getter and setter
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(String coffeeType) {
        this.coffeeType = coffeeType;
    }

    /**
     * Method return all records from orders list.
     *
     * @return
     */
    public List<OrdersListBean> getGetAllOrdersList() {
        List<OrdersListBean> ordersListBean = new ArrayList<>();
        try {
            List<OrderList> orderList = orderListService.getAll();
            for (OrderList ol : orderList) {
                OrdersListBean olb = new OrdersListBean();
                olb.setId(ol.getId());
                olb.setQuantity(ol.getQuantity());
                olb.setAddress(ol.getAddress());
                olb.setDeliveryDate(ol.getDeliveryDate());
                olb.setDelivery(ol.isDelivery());
                olb.setTotalPrice(ol.getTotalPrice());
                olb.setCoffeeType(ol.getCoffeeType().getCoffeeName());
                ordersListBean.add(olb);
                logger.info(" - Object 'OrderList' was add!");
            }
        } catch (ServicesException e) {
            logger.error(" - Object 'OrderList' wasn't add!");
            e.printStackTrace();
        }
        return ordersListBean;
    }

    /**
     * Method return order from orders list by Id.
     */
    public void getById() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        Long id = Long.valueOf(request.getParameter("id"));
        try {
            OrderList orderList = orderListService.getById(id);
            this.setId(id);
            this.setCoffeeType(orderList.getCoffeeType().getCoffeeName());
            this.setQuantity(orderList.getQuantity());
            this.setDelivery(orderList.isDelivery());
            this.setDeliveryDate(orderList.getDeliveryDate());
            this.setAddress(orderList.getAddress());
            this.setTotalPrice(orderList.getTotalPrice());
            logger.info(" - Object 'OrderList' was get by Id!");
        } catch (ServicesException e) {
            logger.info(" - Object 'OrderList' wasn't get by Id!");
            e.printStackTrace();
        }
    }

    /**
     * Method remove order from orders list by id.
     *
     * @throws ServicesException
     */
    public void delete() throws ServicesException {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
            Long id = Long.valueOf(request.getParameter("id"));
            OrderList orderList = orderListService.getById(id);
            orderListService.remove(orderList);
            logger.info(" - Object 'OrderList' was remove by Id!");
        } catch (Exception e) {
            logger.error(" - Object 'OrderList' wasn't remove by Id!");
            e.printStackTrace();
        }
    }

    /**
     * Method add new order to orders list.
     *
     * @throws ServicesException
     */
    public void add() throws ServicesException {

        OrderList orderList = new OrderList();
        FacesContext fc = FacesContext.getCurrentInstance();
        if (this.getAddress() == null || "".equals(this.getAddress())) {
            fc.addMessage("coffeeForm:address",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Пустой адресс", "Вы должны ввести адрес"));
        }
        if (this.getQuantity() < 100.0) {
            fc.addMessage("coffeeForm:quantity",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Неверное колличество", "Вы должны ввести колличество более 100 г."));
        }

        try {
            HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
            orderList.setCoffeeType(coffeeTypeService.getById(1)); // create getByName and parse name
            orderList.setQuantity(this.getQuantity());
            orderList.setDelivery(this.isDelivery());
            orderList.setAddress(this.getAddress());
            orderList.setDeliveryDate(new Date());
            orderList.setTotalPrice(Double.valueOf(request.getParameter("totalPrice")));
            orderListService.add(orderList);
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Заказ добавлен", "Заказ номер " + this.getId()));
            logger.info(" - Object 'OrderList' was add!");
        } catch (Exception e) {
            logger.error(" - Object 'OrderList' wasn't add!");
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Заказ небыл добавлен", e.getMessage()));
            e.printStackTrace();
        }

    }

    public double finalTotalPrice() {
        CoffeeType coffeeType = null; // getByName
        try {
            coffeeType = coffeeTypeService.getById(1);
        } catch (ServicesException e) {
            e.printStackTrace();
        }
        totalPrice = coffeeType.getCoastPerHundredGrams() * quantity;
        if (delivery) totalPrice = totalPrice + 1;
        return totalPrice;
    }

}
