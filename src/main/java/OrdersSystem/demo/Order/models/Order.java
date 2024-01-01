package OrdersSystem.demo.Order.models;

import OrdersSystem.demo.Product.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int orderID;
    private int customerID;
    private String address;
    private OrderStatus orderStatus;
    private double fees;
    private double orderPrice;
    private double totalPrice;
    private LocalDateTime orderDate = LocalDateTime.now();
    private Map<Product, Integer> orderItems = new HashMap<>();

    public Order(int orderID, int customerID, String address, OrderStatus orderStatus, double fees, double orderPrice, double totalPrice, Map<Product, Integer> orderItems) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.address = address;
        this.orderStatus = orderStatus;
        this.fees = fees;
        this.orderPrice = orderPrice;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
    }

    public String getOrderDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return orderDate.format(formatter);
    }
}