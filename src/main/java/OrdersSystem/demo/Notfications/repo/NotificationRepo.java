package OrdersSystem.demo.Notfications.repo;

import OrdersSystem.demo.Notfications.models.CHANNEL;
import OrdersSystem.demo.Auth.repo.UsersRepo;
import OrdersSystem.demo.Notfications.models.LANGUAGES;
import OrdersSystem.demo.Notfications.models.Notification;
import OrdersSystem.demo.Notfications.models.NotificationTemplate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
@Setter
@Getter
public class NotificationRepo{
    public static NotificationRepo notificationRepo;
    private ArrayList<NotificationTemplate> notificationTemplates;
    private ArrayList<Notification> notificationDB;
    private Queue<Notification> notificationQueue;
    private UsersRepo usersRepo;

    //singleton design pattern, used to make sure that there is only one instance of the notification repository.
    public static NotificationRepo getInstance() {
        if (notificationRepo == null) {
            notificationRepo = new NotificationRepo();
        }
        return notificationRepo;
    }
    //initialize notification templates for order confirmation, order shipment, order cancellation.
    public NotificationRepo() {
        notificationTemplates = new ArrayList<NotificationTemplate>();
        notificationDB = new ArrayList<Notification>();
        notificationQueue = new LinkedList<>();
        usersRepo = UsersRepo.getInstance();

        ArrayList<String> placeHolders = new ArrayList<>();
        placeHolders.add("{X}");
        placeHolders.add("{Y}");
        placeHolders.add("{Z}");
        NotificationTemplate notificationTemplate1 = new NotificationTemplate(1, "Order Confirmation", "Dear {X}, Your booking of the order number {Y} is confirmed. Your total price is {Z}. Your order will be shipped within 5 days. You can cancel order within 2 days maximum. Thanks for using our store", placeHolders);
        notificationTemplates.add(notificationTemplate1);

        ArrayList<String> placeHolders2 = new ArrayList<>();
        placeHolders2.add("{X}");
        placeHolders2.add("{Y}");
        NotificationTemplate notificationTemplate2 = new NotificationTemplate(2, "Order Shipment", "Dear {X}, Your order number {Y} is shipped. Thanks for using our store ",  placeHolders2);
        notificationTemplates.add(notificationTemplate2);

        ArrayList<String> placeHolders3 = new ArrayList<>();
        placeHolders3.add("{X}");
        placeHolders3.add("{Y}");
        NotificationTemplate notificationTemplate3 = new NotificationTemplate(3, "Order Cancellation", "Dear {X}, Your order number {Y} is cancelled. and your money refunded to your account. if you face any problem please contact us. Thanks for using our store ",  placeHolders3);
        notificationTemplates.add(notificationTemplate3);
        //initialize notification database with some notifications.
        Notification notification1 = new Notification(notificationTemplate1, LANGUAGES.EN, 20210018, "Dear Reda, Your booking of the order number 4 is confirmed. your total price is 150.0. Your order will be shipped within 5 days. You can cancel order within 2 days maximum. Thanks for using our store", CHANNEL.EMAIL);
        notificationDB.add(notification1);
        Notification notification2 = new Notification(notificationTemplate2, LANGUAGES.AR, 20210018, "Dear Reda, Your order number 4 is shipped. Thanks for using our store ", CHANNEL.SMS);
        notificationDB.add(notification2);
    }
    //get notification template by id.
    public NotificationTemplate getNotificationTemplate(int id) {
        for (NotificationTemplate notificationTemplate : notificationTemplates) {
            if (notificationTemplate.getTemplateID() == id) {
                return notificationTemplate;
            }
        }
        return null;
    }
    //get notification that are of the same channel, used in the statistics.
    public ArrayList<Notification> getNotified(CHANNEL channel) {
        ArrayList<Notification> notifications = new ArrayList<>();
        for (Notification notification : notificationDB) {
            if (notification.getChannel() == channel) {
                notifications.add(notification);
            }
        }
        return notifications;
    }
    //get most notified email or phone that are notified, used in the statistics.
    private Map.Entry<String, Integer> findMostNotified(Map<String, Integer> map) {
        int max = 0;
        Map.Entry<String, Integer> mostNotified = null;

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                mostNotified = entry;
            }
        }
        return mostNotified;
    }
    //calculate number of notifications for each email or phone, used in the statistics.
    private Map<String, Integer> calculateExistence(ArrayList<Notification> notifications, Function<Integer, String> contactInfoProvider) {
        Map<String, Integer> map = new HashMap<>();
        for (Notification notification : notifications) {
            int CID = notification.getUserID();
            String contactInfo = contactInfoProvider.apply(CID);
            if (map.containsKey(contactInfo)) {
                map.put(contactInfo, map.get(contactInfo) + 1);
            } else {
                map.put(contactInfo, 1);
            }
        }
        return map;
    }
    //get most notified email or phone, used in the statistics.
    public Object mostNotifiedEmailOrPhone() {
        ArrayList<Notification> emails = getNotified(CHANNEL.EMAIL);
        ArrayList<Notification> phones = getNotified(CHANNEL.SMS);
        Map<String, Integer> emailsMap = calculateExistence(emails, (CID) -> usersRepo.getAccountManager(CID).getUser().getEmail());
        Map<String, Integer> phonesMap = calculateExistence(phones, (CID) -> usersRepo.getAccountManager(CID).getUser().getPhone());
        Map.Entry<String, Integer> mostNotifiedEmail = findMostNotified(emailsMap);
        Map.Entry<String, Integer> mostNotifiedPhone = findMostNotified(phonesMap);
            Map<String, String> result = new HashMap<>();
        if (mostNotifiedEmail != null && mostNotifiedPhone != null) {
            if (mostNotifiedEmail.getValue() > mostNotifiedPhone.getValue()) {
                result.put("Most Notified Email", mostNotifiedEmail.getKey());
                return result;
            } else if (mostNotifiedPhone.getValue() > mostNotifiedEmail.getValue()) {
                result.put("Most Notified Phone", mostNotifiedPhone.getKey());
                return result;
            } else {
                result.put("Most Notified Email", mostNotifiedEmail.getKey());
                result.put("Most Notified Phone", mostNotifiedPhone.getKey());
                return result;
            }
        } else if (mostNotifiedPhone == null) {
            return mostNotifiedEmail.getKey();
        } else if (mostNotifiedEmail == null){
            return mostNotifiedPhone.getKey();
        } else {
            return "No notifications";
        }
    }
    //get most notified template, calculated by number of notifications stored in the database, used in the statistics.
    public Object mostNotifiedTemplate() {
        Map<NotificationTemplate, Integer> notificationsMap = new HashMap<>();
        for (Notification temp : notificationDB) {
            if (notificationsMap.containsKey(temp.getNotificationTemplate())) {
                notificationsMap.put(temp.getNotificationTemplate(), notificationsMap.get(temp.getNotificationTemplate()) + 1);
            } else {
                notificationsMap.put(temp.getNotificationTemplate(), 1);
            }
        }
        int max = 0;
        NotificationTemplate mostNotifiedTemplate = null;
        for (Map.Entry<NotificationTemplate, Integer> entry : notificationsMap.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                mostNotifiedTemplate = entry.getKey();
            }
        }
        if (mostNotifiedTemplate != null) {
            return mostNotifiedTemplate;
        } else {
            return "No notifications";
        }
    }
}
