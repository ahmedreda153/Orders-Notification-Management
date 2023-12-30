package OrdersSystem.demo.Auth.controllers;

import OrdersSystem.demo.Auth.models.AccountManager;
import OrdersSystem.demo.Auth.models.User;
import OrdersSystem.demo.Order.models.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Map;

public interface IAuthenticationController {
    public AccountManager login(Map<String, String> body);
//    public ArrayList<User> register(User user);
    public String register(User user);

    public ArrayList<ArrayList<Order>> getOrdersHistory(int customerID);
}
