package sensesnet.coffeeShop.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "ORDER_COFFEE_LIST")
public class OrderList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "quantity", nullable = false)
    @NotNull(message = "Coffee type can't be empty!")
    private double quantity;

    @Column(name = "address", unique = true, nullable = false, length = 45)
    @NotNull(message = "Address can't be empty!")
    private String address;

    @Column(name = "delivery_date")
//    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;

    @Column(name = "delivery", nullable = false)
    private boolean delivery;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coffee_Name")
    private CoffeeType coffeeType;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CoffeeType getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(CoffeeType coffeeType) {
        this.coffeeType = coffeeType;
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
}
