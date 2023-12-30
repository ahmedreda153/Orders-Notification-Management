package OrdersSystem.demo.Auth.repo;
import OrdersSystem.demo.Auth.models.AccountManager;
import OrdersSystem.demo.Auth.models.User;
import OrdersSystem.demo.Notfications.models.NotificationTemplate;
import OrdersSystem.demo.Notfications.repo.NotificationRepo;
import OrdersSystem.demo.Order.models.Order;
import OrdersSystem.demo.Order.models.Product;
import OrdersSystem.demo.Order.repo.OrderRepo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UsersRepo {
    private static UsersRepo usersRepo;
    private OrderRepo orderRepo;
    private NotificationRepo notificationRepo;
    public ArrayList<User> userTable;
    public ArrayList<AccountManager> accountManagers;

    public static UsersRepo getInstance() {
        if (usersRepo == null) {
            usersRepo = new UsersRepo();
        }
        return usersRepo;
    }

    public UsersRepo() {
        orderRepo = OrderRepo.getInstance();
        notificationRepo = NotificationRepo.getInstance();
        userTable = new ArrayList<User>();
        accountManagers = new ArrayList<AccountManager>();
        userTable.add(new User(20210161,"Salma","salmamghany@gmail.com","Salma123.",3500));
        accountManagers.add(new AccountManager(20210161,userTable.get(0),new ArrayList<>(), new ArrayList<>()));
        userTable.add(new User(20210121,"Habiba","habibaalaa03@gmail.com","Habiba123.",4500));
        accountManagers.add(new AccountManager(20210121,userTable.get(1),new ArrayList<>(), new ArrayList<>()));
        userTable.add(new User(20210175,"Shrouk","shroukmghany@gmail.com","Shrouk123.",5500));
        accountManagers.add(new AccountManager(20210175,userTable.get(2),new ArrayList<>(), new ArrayList<>()));
        userTable.add(new User(20210018,"Reda","redamghany@gmail.com","Reda123.",6500));
        accountManagers.add(new AccountManager(20210018,userTable.get(3), new ArrayList<>(), new ArrayList<>()));
        for (AccountManager acc : accountManagers) {
            ArrayList<ArrayList<Order>> orders = orderRepo.getOrders();
            ArrayList<NotificationTemplate> notificationTemplates = notificationRepo.getNotificationTemplates();
            for (ArrayList<Order> order : orders) {
                if (order.get(0).getCustomerID() == acc.getUser().getId()) {
                    acc.getOrdersHistory().add(order);
                }
            }
            for (NotificationTemplate notificationTemplate : notificationTemplates) {
                if (notificationTemplate.getUserID() == acc.getUser().getId()) {
                    acc.getNotificationsHistory().add(notificationTemplate);
                }
            }
        }
    }

    public AccountManager getAccountManager(String username, String password) {
        for (AccountManager accountManager : accountManagers) {
            if (accountManager.getUser().getName().equals(username) && accountManager.getUser().getPassword().equals(password)) {
                return accountManager;
            }
        }
        return null;
    }

    public AccountManager getAccountManager(int id) {
        for (AccountManager accountManager : accountManagers) {
            if (accountManager.getUser().getId() == id) {
                return accountManager;
            }
        }
        return null;
    }

    public User checkUserExistance(String username, String password) {
        for (User user : userTable) {
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public User checkUserExistance(int id) {
        for (User user : userTable) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public Boolean addUser(User user) {
        if (checkUserExistance(user.getName(), user.getPassword()) == null && checkUserExistance(user.getId()) == null) {
            userTable.add(user);
            accountManagers.add(new AccountManager(user.getId(), user, new ArrayList<>(), new ArrayList<>()));
            return true;
        }
        return false;
    }

    public ArrayList<ArrayList<Order>> getOrdersHistory(int id) {
        return getAccountManager(id).getOrdersHistory();
    }
}