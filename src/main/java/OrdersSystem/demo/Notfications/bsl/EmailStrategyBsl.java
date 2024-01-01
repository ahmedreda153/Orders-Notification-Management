package OrdersSystem.demo.Notfications.bsl;

import OrdersSystem.demo.Auth.bsl.AccountManagerBsl;
import OrdersSystem.demo.Notfications.models.Notification;
import org.springframework.stereotype.Service;

@Service
public class EmailStrategyBsl implements ChannelBsl{
    //send notification to customer by email
    @Override
    public void send(Notification notification, AccountManagerBsl accountManagerBsl) {
        accountManagerBsl.notifyCustomer(notification);
    }
}
