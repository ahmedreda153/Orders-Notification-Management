package OrdersSystem.demo.Notfications.bsl;

import OrdersSystem.demo.Auth.bsl.AccountManagerBsl;
import OrdersSystem.demo.Notfications.models.Notification;

public interface ChannelBsl {
    void send(Notification notification, AccountManagerBsl accountManagerBsl);
}
