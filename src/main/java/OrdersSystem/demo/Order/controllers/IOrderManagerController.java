package OrdersSystem.demo.Order.controllers;

import OrdersSystem.demo.Order.models.Order;
import OrdersSystem.demo.Order.models.OrderCriteria;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Map;

public interface IOrderManagerController {
    Object placeSimpleOrder(@RequestBody OrderCriteria orderCriteria);
    Object placeCompoundOrder(@RequestBody ArrayList<OrderCriteria> orderCriterias);
    ArrayList<ArrayList<Order>> getOrders();
    ArrayList<ArrayList<Order>> getSimpleOrders();
    ArrayList<ArrayList<Order>> getCompoundOrders();
    Object shipOrder(@RequestBody Map<String, String> body);
    Object cancelOrder(@RequestBody Map<String, String> body);
}
