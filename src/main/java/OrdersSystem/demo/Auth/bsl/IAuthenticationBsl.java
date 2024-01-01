package OrdersSystem.demo.Auth.bsl;

import OrdersSystem.demo.Auth.models.User;
import OrdersSystem.demo.Order.models.Order;

import java.util.ArrayList;

public interface IAuthenticationBsl {
    public Object login(String email, String password);
    public String register(User user);
    public ArrayList<ArrayList<Order>> getOrdersHistory(int customerID);
    public ArrayList<User> allUsers();
}
