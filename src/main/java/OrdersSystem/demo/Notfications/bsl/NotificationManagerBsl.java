package OrdersSystem.demo.Notfications.bsl;

import OrdersSystem.demo.Auth.models.AccountManager;
import OrdersSystem.demo.Notfications.models.NotificationTemplate;

import java.util.ArrayList;

public class NotificationManagerBsl implements  INotificationManagerBsl {
    private final ArrayList<AccountManager> accountManagers;
    private final ChannelBsl channelBsl;

    public NotificationManagerBsl(ChannelBsl channelBsl, ArrayList<AccountManager> accountManagers) {
        this.channelBsl = channelBsl;
        this.accountManagers = accountManagers;
    }

    public void addAccountManager(AccountManager accountManager) {
        accountManagers.add(accountManager);
    }

    public void removeAccountManager(AccountManager accountManager) {
        accountManagers.remove(accountManager);
    }

    public void sendNotification(NotificationTemplate notificationTemplate) {
       for (AccountManager accountManager : accountManagers) {
           channelBsl.sendNotification(notificationTemplate, accountManager);
       }
    }
}
