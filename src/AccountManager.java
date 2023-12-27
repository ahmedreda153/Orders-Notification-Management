import java.util.ArrayList;

public class AccountManager {
    private User user;
    private ArrayList<Order> ordersHistory;
    private ArrayList<Notification> notifications;

    public AccountManager(User user, ArrayList<Order> ordersHistory,ArrayList<Notification> notifications) {
        this.user = user;
        this.ordersHistory = ordersHistory;
        this.notifications = notifications;
    }
    
    public void addOrderToHistory(Order order) {
        this.ordersHistory.add(order);
    }
    
    public void addNotification(Notification notification) {
        this.notifications.add(notification);
    }

    public void setOrdersHistory(ArrayList<Order> ordersHistory) {
        this.ordersHistory = ordersHistory;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Order> getOrdersHistory() {
        return ordersHistory;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void printOrdersHistory() {
        for (Order order : ordersHistory) {
            System.out.println(order);
        }
    }

    public void printNotifications() {
        for (Notification notification : notifications) {
            System.out.println(notification);
        }
    }

    public void placeOrder(Order order) {
        double diff = user.getBalance() - order.getOrderPrice();
        if (diff >= 0) {
            user.deductBalance(order.getOrderPrice());
            //notification
            ordersHistory.add(order);
        }else{
            System.out.println("Insufficient balance");
        }
    }
}
