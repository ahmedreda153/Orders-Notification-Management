package OrdersSystem.demo.Notfications.repo;

import OrdersSystem.demo.Notfications.models.NotificationTemplate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

@Component
@Setter
@Getter
public class NotificationRepo{
    public static NotificationRepo notificationRepo;

    private ArrayList<NotificationTemplate> notificationTemplates;
    private Queue<NotificationTemplate> notificationQueue;

    public static NotificationRepo getInstance() {
        if (notificationRepo == null) {
            notificationRepo = new NotificationRepo();
        }
        return notificationRepo;
    }

    public NotificationRepo() {
        notificationTemplates = new ArrayList<NotificationTemplate>();
        notificationQueue = new LinkedList<>();
        notificationTemplates.add(new NotificationTemplate(1,"Order Confirmation"," Dear, your booking of theis confirmed. thanks for using our store","English", "Salma", 20210161, "Shoes"));
        notificationTemplates.add(new NotificationTemplate(2,"Order Confirmation"," Dear, your booking of the is confirmed. thanks for using our store","Arabic", "Salma", 20210161, "Shoes"));
        notificationTemplates.add(new NotificationTemplate(3,"Order Shipping"," Dear, your booking of the is shipped. thanks for using our store","English", "Salma", 20210161, "Shoes"));
        notificationTemplates.add(new NotificationTemplate(4,"Order Shipping"," Dear, your booking of the is shipped. thanks for using our store","Arabic", "Salma", 20210161, "Shoes"));
    }

    public NotificationTemplate getNotificationTemplate(int id) {
        for (NotificationTemplate notificationTemplate : notificationTemplates) {
            if (notificationTemplate.getTemplateID() == id) {
                return notificationTemplate;
            }
        }
        return null;
    }

}
