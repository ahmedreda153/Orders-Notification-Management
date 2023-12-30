package OrdersSystem.demo.Notfications.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationTemplate {
    private int templateID;
    private String subject;
    private String content;
    private String language;
    private String userName;
    private int userID;
    private String ItemName;
}
