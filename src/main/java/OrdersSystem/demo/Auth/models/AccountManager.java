package OrdersSystem.demo.Auth.models;

import OrdersSystem.demo.Notfications.models.CHANNEL;
import OrdersSystem.demo.Notfications.models.LANGUAGES;
import OrdersSystem.demo.Notfications.models.Notification;
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
    private LANGUAGES language;
    private CHANNEL channel;
    private ArrayList<ArrayList<Order>> ordersHistory;
    private ArrayList<Notification> notifications;
}