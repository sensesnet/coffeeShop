package sensesnet.coffeeShop.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "COFFEE_TYPE")
public class CoffeeType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "coffee_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "coffee_Name", unique = true, nullable = false, length = 45)
    @NotNull(message = "Coffe type can't be empty!")
    private String coffeeName;

    @Column(name = "coast_per_100g",nullable = false)
    @Min(value = 1, message = "Price (by 100 g coffee) must not be zero!")
    private double coastPerHundredGrams;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "coffeeType")
    private List<OrderList> orderedCoffee;

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

    public List<OrderList> getOrderedCoffee() {
        return orderedCoffee;
    }

    public void setOrderedCoffee(List<OrderList> orderedCoffee) {
        this.orderedCoffee = orderedCoffee;
    }

    @Override
    public String toString() {
        return "CoffeeType{" +
                "id=" + id +
                ", coffeeName='" + coffeeName + '\'' +
                ", coastPerHundredGrams=" + coastPerHundredGrams +
                ", orderedCoffee=" + orderedCoffee +
                '}';
    }
}
