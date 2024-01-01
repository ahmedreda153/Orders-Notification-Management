package OrdersSystem.demo.Notfications.controllers;

import OrdersSystem.demo.Notfications.models.Notification;

import java.util.Queue;

public interface INotificationManagerController {
    Queue<Notification> getNotificationQueue();
    String sendNotification();
    Object getMostNotified();
    Object getMostNotifiedTemp();
}
