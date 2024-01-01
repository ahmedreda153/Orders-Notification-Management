package OrdersSystem.demo.Notfications.controllers;

import OrdersSystem.demo.Notfications.bsl.INotificationManagerBsl;
import OrdersSystem.demo.Notfications.bsl.NotificationManagerBsl;
import OrdersSystem.demo.Notfications.models.Notification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Queue;

@RestController
public class NotificationManagerController implements INotificationManagerController {
    private INotificationManagerBsl notificationManagerBsl;

    public NotificationManagerController() {
        this.notificationManagerBsl = new NotificationManagerBsl();
    }
    //API to get the notification to be sent that is stored in the queue
    @GetMapping("/getNotificationsQueue")
    public Queue<Notification> getNotificationQueue() {
        return notificationManagerBsl.getNotificationQueue();
    }
    //API to send one notification from the queue
    @PostMapping("/sendNotification")
    public String sendNotification() {
        return notificationManagerBsl.sendNotification();
    }
    //API to get all the notifications that were sent to users in the past, stored in the DB
    @GetMapping("/getNotificationDB")
    public ArrayList<Notification> getNotificationDB(){
        return notificationManagerBsl.getNotificationDB();
    }
    //API to get the most notified email or phone number
    @GetMapping("/getMostNotifiedEmail-Phone")
    public Object getMostNotified() {
        return notificationManagerBsl.getMostNotified();
    }
    //API to get the most sent template notification
    @GetMapping("/getMostNotifiedTemplate")
    public Object getMostNotifiedTemp() {
        return  notificationManagerBsl.getMostNotifiedTemp();
    }
}
