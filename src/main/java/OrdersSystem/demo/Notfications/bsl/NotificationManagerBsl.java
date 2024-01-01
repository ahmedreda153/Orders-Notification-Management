package OrdersSystem.demo.Notfications.bsl;

import OrdersSystem.demo.Auth.bsl.AccountManagerBsl;
import OrdersSystem.demo.Auth.models.AccountManager;
import OrdersSystem.demo.Auth.repo.UsersRepo;
import OrdersSystem.demo.Notfications.models.Notification;
import OrdersSystem.demo.Notfications.models.NotificationTemplate;
import OrdersSystem.demo.Notfications.repo.NotificationRepo;
import OrdersSystem.demo.Order.models.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Queue;

@Service
public class NotificationManagerBsl implements INotificationManagerBsl {
    private final ArrayList<AccountManager> accountManagers;
    private NotificationRepo notificationRepo;
    private UsersRepo usersRepo;
    private ChannelBsl channelBsl;

    public NotificationManagerBsl() {
        this.accountManagers = new ArrayList<>();
        this.notificationRepo = NotificationRepo.getInstance();
        this.usersRepo = UsersRepo.getInstance();
    }
    //add account manager to list of account managers in case of compound order
    public void addAccountManager(AccountManager accountManager) {
        accountManagers.add(accountManager);
    }

    //add notification to queue to be sent, and in case of compound order, add notification for each order in queue.
    public void addToQueue(Order order, int id, NotificationTemplateBsl notificationTemplateBsl) {
        NotificationTemplate notificationTemplate = notificationRepo.getNotificationTemplate(id);
        String content = notificationTemplateBsl.prepareContent(order, notificationTemplate);
        for (AccountManager accountManager : accountManagers) {
            Notification notification = new Notification(notificationTemplate, accountManager.getLanguage(), order.getCustomerID(), content, accountManager.getChannel());
            notificationRepo.getNotificationQueue().add(notification);
       }
    }
    //get all notifications to be sent from queue
    public Queue<Notification> getNotificationQueue() {
        return notificationRepo.getNotificationQueue();
    }
    //get all notifications already sent from DB
    public ArrayList<Notification> getNotificationDB() {
        return notificationRepo.getNotificationDB();
    }
    //poll one notification from queue, and send it to the customer, then add it to DB  
    public String sendNotification() {
        if (!notificationRepo.getNotificationQueue().isEmpty()) {
            Notification notification = notificationRepo.getNotificationQueue().poll();
            notificationRepo.getNotificationDB().add(notification);
            AccountManager accountManager = usersRepo.getAccountManager(notification.getUserID());
            AccountManagerBsl accountManagerBsl = new AccountManagerBsl(accountManager);
            channelBsl = accountManagerBsl.getChannelBsl();
            channelBsl.send(notification, accountManagerBsl);
//            System.out.println(notification.getMsg());
            return notification.getMsg();
        }
        return "No notifications to send, queue is empty";
    }

    // check notifications queue every 30 seconds
    @Scheduled(fixedRate = 30000)
    public void queueScheduled() {
        if (!notificationRepo.getNotificationQueue().isEmpty())
        {
            sendNotification();
        }
    }

    //get most notified email or phone number from DB (most notified customer) 
    public Object getMostNotified() {
        return notificationRepo.mostNotifiedEmailOrPhone();
    }
    //get most sent notification template from DB
    public Object getMostNotifiedTemp() {
        return notificationRepo.mostNotifiedTemplate();
    }
}
