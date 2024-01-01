package OrdersSystem.demo.Auth.repo;
import OrdersSystem.demo.Auth.models.AccountManager;
import OrdersSystem.demo.Notfications.models.CHANNEL;
import OrdersSystem.demo.Auth.models.User;
import OrdersSystem.demo.Notfications.models.LANGUAGES;
import OrdersSystem.demo.Order.models.Order;
import OrdersSystem.demo.Order.repo.OrderRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Random;

@Component
@Setter
@Getter
public class UsersRepo {
    private static UsersRepo usersRepo;
    private OrderRepo orderRepo;
    private ArrayList<User> userTable;
    private ArrayList<AccountManager> accountManagers;

    public static UsersRepo getInstance() {
        if (usersRepo == null) {
            usersRepo = new UsersRepo();
        }
        return usersRepo;
    }

    public UsersRepo() {
        orderRepo = OrderRepo.getInstance();
        userTable = new ArrayList<User>();
        accountManagers = new ArrayList<AccountManager>();
        userTable.add(new User(20210161,"Salma","salmamghany@gmail.com", "01141114798", "Salma123.",3500));
        accountManagers.add(new AccountManager(20210161,userTable.get(0), LANGUAGES.EN, CHANNEL.EMAIL, new ArrayList<>(), new ArrayList<>()));
        userTable.add(new User(20210121,"Habiba","habibaalaa03@gmail.com", "01143332642", "Habiba123",4500));
        accountManagers.add(new AccountManager(20210121,userTable.get(1), LANGUAGES.AR, CHANNEL.SMS, new ArrayList<>(), new ArrayList<>()));
        userTable.add(new User(20210175,"Shrouk","shroukmghany@gmail.com", "01149677992", "Shrouk123.",5500));
        accountManagers.add(new AccountManager(20210175,userTable.get(2), LANGUAGES.EN, CHANNEL.EMAIL, new ArrayList<>(), new ArrayList<>()));
        userTable.add(new User(20210018,"Reda","ahmedreda5338@gmail.com", "01002744648", "Reda123.",6500));
        accountManagers.add(new AccountManager(20210018,userTable.get(3), LANGUAGES.AR, CHANNEL.SMS, new ArrayList<>(), new ArrayList<>()));
        for (AccountManager acc : accountManagers) {
            ArrayList<ArrayList<Order>> orders = orderRepo.getOrders();
            for (ArrayList<Order> order : orders) {
                if (order.get(0).getCustomerID() == acc.getUser().getId()) {
                    acc.getOrdersHistory().add(order);
                }
            }
        }
    }

    // get account manager by email and password
    public AccountManager getAccountManager(String email, String password) {
        for (AccountManager accountManager : accountManagers) {
            if (accountManager.getUser().getEmail().equals(email) && accountManager.getUser().getPassword().equals(password)) {
                return accountManager;
            }
        }
        return null;
    }

    // get account manager by id
    public AccountManager getAccountManager(int id) {
        for (AccountManager accountManager : accountManagers) {
            if (accountManager.getUser().getId() == id) {
                return accountManager;
            }
        }
        return null;
    }

    // get user by email and password
    public User checkUserExistence(String email, String password) {
        for (User user : userTable) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    // check if email is unique
    public Boolean checkUniqueEmail(String email) {
        for (User user : userTable) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    // randomize language
    public LANGUAGES randomLanguage() {
        Random random = new Random();
        int randomInt = random.nextInt(2);
        if (randomInt == 0) {
            return LANGUAGES.EN;
        } else {
            return LANGUAGES.AR;
        }
    }

    // randomize channel
    public CHANNEL randomChannel() {
        Random random = new Random();
        int randomInt = random.nextInt(2);
        if (randomInt == 0) {
            return CHANNEL.EMAIL;
        } else {
            return CHANNEL.SMS;
        }
    }

    // add user to user table and account manager to account managers table
    public void addUser(User user) {
        userTable.add(user);
        accountManagers.add(new AccountManager(user.getId(), user, randomLanguage(), randomChannel(), new ArrayList<>(), new ArrayList<>()));
    }

    // get orders history of a specific user
    public ArrayList<ArrayList<Order>> getOrdersHistory(int id) {
        return getAccountManager(id).getOrdersHistory();
    }
}