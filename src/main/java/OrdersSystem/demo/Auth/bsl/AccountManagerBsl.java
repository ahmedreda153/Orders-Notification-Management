package OrdersSystem.demo.Auth.bsl;

import OrdersSystem.demo.Auth.models.AccountManager;
import OrdersSystem.demo.Notfications.bsl.ChannelBsl;
import OrdersSystem.demo.Notfications.bsl.EmailStrategyBsl;
import OrdersSystem.demo.Notfications.bsl.SmsStrategyBsl;
import OrdersSystem.demo.Notfications.models.CHANNEL;
import OrdersSystem.demo.Notfications.models.Notification;
import OrdersSystem.demo.Notfications.repo.NotificationRepo;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AccountManagerBsl {
    private AccountManager accountManager;
    private NotificationRepo notificationRepo;
    private ChannelBsl channelBsl;

    public AccountManagerBsl(AccountManager accountManager) {
        this.accountManager = accountManager;
        this.notificationRepo = NotificationRepo.getInstance();
        if (accountManager.getChannel() == CHANNEL.SMS) {
            this.channelBsl = new SmsStrategyBsl();
        } else if (accountManager.getChannel() == CHANNEL.EMAIL){
            this.channelBsl = new EmailStrategyBsl();
        }
    }

    public void deductBalance(double amount) {
        accountManager.getUser().setBalance(accountManager.getUser().getBalance() - amount);
    }
    
    public void addBalance(double amount) {
        accountManager.getUser().setBalance(accountManager.getUser().getBalance() + amount);
    }

    public void notifyCustomer(Notification notification) {
        accountManager.getNotifications().add(notification);
    }
}
