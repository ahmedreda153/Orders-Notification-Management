package OrdersSystem.demo.Auth.models;

import OrdersSystem.demo.Notfications.models.NotificationTemplate;
import OrdersSystem.demo.Order.models.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountManager {
    private int id;
    private User user;
    private ArrayList<ArrayList<Order>> ordersHistory;
    private ArrayList<NotificationTemplate> notificationsHistory;
}
