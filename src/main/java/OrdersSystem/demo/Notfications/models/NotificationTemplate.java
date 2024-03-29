package OrdersSystem.demo.Notfications.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationTemplate{
    private int templateID;
    private String subject;
    private String content;
    private ArrayList<String> placeHolders;
}
