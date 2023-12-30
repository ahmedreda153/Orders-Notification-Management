package OrdersSystem.demo.Auth.bsl;

import OrdersSystem.demo.Auth.models.AccountManager;
import OrdersSystem.demo.Auth.models.User;
import OrdersSystem.demo.Auth.repo.UsersRepo;
import OrdersSystem.demo.Order.models.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationBsl implements IAuthenticationBsl{
    private UsersRepo userRepo;
    private IValidatorBsl validatorBsl;

    public AuthenticationBsl() {
        userRepo = UsersRepo.getInstance();
        validatorBsl = new ValidatorBsl();
    }

    @Override
    public AccountManager login(String username, String password) {
        User user = userRepo.checkUserExistance(username, password);
        if (user != null) {
            return userRepo.getAccountManager(username, password);
        } else {
            return null;
        }
    }

    public ArrayList<User> allUsers(){
        return userRepo.userTable;
    }

    @Override
    public String register(User user) {
        if (validatorBsl.validatEmail(user.getEmail())) {
            if (validatorBsl.validatPassword(user.getPassword())) {
                if (userRepo.addUser(user))
                    return "User registered successfully";
                else
                    return "User already exists";
            } else {
                return "Password must be at least 8 characters and alphanumeric and contain a special character";
            }
        } else {
            return "Email must be at least 8 characters and in the correct format";
        }
    }

    @Override
    public ArrayList<ArrayList<Order>> getOrdersHistory(int customerID) {
        return userRepo.getOrdersHistory(customerID);
    }
}
