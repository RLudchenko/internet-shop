package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long orderID;
    private Long userId;
    private List<Product> products = new ArrayList<>();

    public Order(Long user, List<Product> products) {
        this.userId = user;
        this.products = products;
    }

    public Long getId() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public Long getUser() {
        return userId;
    }

    public void setUser(Long user) {
        this.userId = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{"
                + "orderID=" + orderID
                + ", user=" + userId
                + ", products=" + products
                + '}';
    }
}
