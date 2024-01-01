package OrdersSystem.demo.Auth.controllers;

import OrdersSystem.demo.Auth.models.User;
import OrdersSystem.demo.Order.models.Order;

import java.util.ArrayList;
import java.util.Map;

public interface IAuthenticationController {
    public Object login(Map<String, String> body);
    public String register(User user);
    public ArrayList<ArrayList<Order>> getOrdersHistory(int customerID);
}
