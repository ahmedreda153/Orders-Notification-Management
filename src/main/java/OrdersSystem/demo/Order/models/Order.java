package OrdersSystem.demo.Order.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private Map<Product, Integer> orderItems = new HashMap<>();
}