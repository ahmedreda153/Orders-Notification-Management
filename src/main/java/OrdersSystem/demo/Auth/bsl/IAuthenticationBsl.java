package OrdersSystem.demo.Auth.bsl;

import OrdersSystem.demo.Auth.models.AccountManager;
import OrdersSystem.demo.Auth.models.User;
import OrdersSystem.demo.Order.models.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

public interface IAuthenticationBsl {
    public AccountManager login(String username, String password);
    public String register(User user);
    public ArrayList<ArrayList<Order>> getOrdersHistory(int customerID);
    public ArrayList<User> allUsers();
}
