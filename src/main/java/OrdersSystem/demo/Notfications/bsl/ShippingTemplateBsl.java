package OrdersSystem.demo.Notfications.bsl;

import OrdersSystem.demo.Notfications.models.NotificationTemplate;
import OrdersSystem.demo.Order.models.Order;

public class ShippingTemplateBsl extends NotificationTemplateBsl{
    //prepareContent method is used to replace the placeholders in the template with the actual values from the order to be sent to the customer
    @Override
    public String prepareContent(Order order, NotificationTemplate notificationTemplate) {
        String content = notificationTemplate.getContent();
        for (String placeholder : notificationTemplate.getPlaceHolders()) {
            switch (placeholder) {
                case "{X}":
                    content = replacePlaceholder(content, placeholder, usersRepo.getAccountManager(order.getCustomerID()).getUser().getName());
                    break;
                case "{Y}":
                    content = replacePlaceholder(content, placeholder, String.valueOf(order.getOrderID()));
                    break;
            }
        }
        return content;
    }
}
