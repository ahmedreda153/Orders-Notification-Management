package OrdersSystem.demo.Notfications.bsl;

import OrdersSystem.demo.Auth.models.AccountManager;
import OrdersSystem.demo.Notfications.models.Notification;
import OrdersSystem.demo.Order.models.Order;

import java.util.ArrayList;
import java.util.Queue;

public interface INotificationManagerBsl {
    void addAccountManager(AccountManager accountManagerBsl);
    void addToQueue(Order order, int id, NotificationTemplateBsl notificationTemplateBsl);
    String sendNotification();
    ArrayList<Notification> getNotificationDB();
    Queue<Notification> getNotificationQueue();
    Object getMostNotified();
    Object getMostNotifiedTemp();
}
