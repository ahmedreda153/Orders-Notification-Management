package OrdersSystem.demo.Notfications.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    private NotificationTemplate notificationTemplate;
    private LANGUAGES language;
    private int userID;
    private String msg;
    private CHANNEL channel;
}
