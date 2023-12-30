package OrdersSystem.demo.Notfications.bsl;

import OrdersSystem.demo.Auth.models.AccountManager;
import OrdersSystem.demo.Notfications.models.NotificationTemplate;
import OrdersSystem.demo.Notfications.repo.NotificationRepo;
import org.springframework.stereotype.Service;

@Service
public class SmsStrategyBsl implements ChannelBsl {
    private NotificationRepo notificationRepo;

    public SmsStrategyBsl() {
        this.notificationRepo = new NotificationRepo();
    }

    @Override
    public void sendNotification(NotificationTemplate notificationTemplate, AccountManager accountManager) {
        notificationTemplate.setUserID(accountManager.getId());
        notificationRepo.getNotificationQueue().add(notificationTemplate);
    }
}
