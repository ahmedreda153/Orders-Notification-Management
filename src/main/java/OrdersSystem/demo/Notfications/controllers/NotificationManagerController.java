package OrdersSystem.demo.Notfications.controllers;

import OrdersSystem.demo.Auth.models.AccountManager;
import OrdersSystem.demo.Notfications.bsl.ChannelBsl;
import OrdersSystem.demo.Notfications.bsl.INotificationManagerBsl;
import OrdersSystem.demo.Notfications.bsl.NotificationManagerBsl;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

public class NotificationManagerController implements INotificationManagerController {
    private INotificationManagerBsl notificationManagerBsl;

    public NotificationManagerController(ChannelBsl channelBsl, ArrayList<AccountManager> accountManagers) {
        this.notificationManagerBsl = new NotificationManagerBsl(channelBsl, accountManagers);
    }


}
