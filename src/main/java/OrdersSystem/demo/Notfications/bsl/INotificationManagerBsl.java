package OrdersSystem.demo.Notfications.bsl;

import OrdersSystem.demo.Auth.models.AccountManager;
import OrdersSystem.demo.Notfications.models.NotificationTemplate;

public interface INotificationManagerBsl {
    void addAccountManager(AccountManager accountManagerBsl);
    void removeAccountManager(AccountManager accountManagerBsl);
    void sendNotification(NotificationTemplate notificationTemplateBsl);
}
