package OrdersSystem.demo.Notfications.bsl;

import OrdersSystem.demo.Auth.models.AccountManager;
import OrdersSystem.demo.Notfications.models.NotificationTemplate;

public interface ChannelBsl {

    public void sendNotification(NotificationTemplate notificationTemplate, AccountManager accountManager);
    
}
