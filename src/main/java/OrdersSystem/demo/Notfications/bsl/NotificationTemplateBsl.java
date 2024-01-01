package OrdersSystem.demo.Notfications.bsl;

import OrdersSystem.demo.Auth.repo.UsersRepo;
import OrdersSystem.demo.Notfications.models.NotificationTemplate;
import OrdersSystem.demo.Order.models.Order;

public abstract class NotificationTemplateBsl {
    public UsersRepo usersRepo = UsersRepo.getInstance();

    public abstract String prepareContent(Order order, NotificationTemplate notificationTemplate);
    public String replacePlaceholder(String content, String placeholder, String replacement) {
        return content.replace(placeholder, replacement);
    }
}
