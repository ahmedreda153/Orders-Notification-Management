import java.util.ArrayList;

public class OrdersSystem {
    private IAuthentication authentication;
    private AccountManager accountManager;
    

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public void login(String email, String password) {
        User user = authentication.login(email, password);
        if (user != null) {
            accountManager = new AccountManager(user, new ArrayList<Order>(), new ArrayList<Notification>());
        }
    }

    public void register(User user) {
        if (authentication.register(user)) {
            accountManager = new AccountManager(user, new ArrayList<Order>(), new ArrayList<Notification>());
        }
    }

    public void logout() {
        accountManager = null;
    }

    public void placeSimpleOrder(Order order) {
        accountManager.placeOrder(order);
        accountManager.addOrderToHistory(order);
    }

    public void placeCompoundOrder(CompoundOrder order, AccountManager firstFriend, AccountManager secondFriend) {
        ArrayList<Order> orders = order.getOrders();
        accountManager.placeOrder(orders.get(0));
        accountManager.addOrderToHistory(order);
        firstFriend.placeOrder(orders.get(1));
        secondFriend.placeOrder(orders.get(2));
    }
}
