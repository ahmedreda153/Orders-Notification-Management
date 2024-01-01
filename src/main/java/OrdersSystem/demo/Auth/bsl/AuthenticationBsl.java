package OrdersSystem.demo.Auth.bsl;

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
    //take username and password and check if they exist in the database and return the account manager or null if not exist
    @Override
    public Object login(String email, String password) {
        User user = userRepo.checkUserExistence(email, password);
        if (user != null) {
            return userRepo.getAccountManager(email, password);
        } else {
            return "User not found";
        }
    }
    //return all users in the database
    public ArrayList<User> allUsers(){
        return userRepo.getUserTable();
    }
    //take user object and check if the email and password are valid and if the user is not exist in the database add it and return a message
    @Override
    public String register(User user) {
        if (validatorBsl.validatEmail(user.getEmail())) {
            if (validatorBsl.validatPassword(user.getPassword())) {
                if (userRepo.checkUniqueEmail(user.getEmail())) {
                    userRepo.addUser(user);
                    return "User registered successfully";
                }
                else{
                    return "User already exists";
                }
            } else {
                return "Password must be at least 8 characters and alphanumeric and contain a special character";
            }
        } else {
            return "Email must be at least 8 characters and in the correct format";
        }
    }

    //take user id and return all orders for this user
    @Override
    public ArrayList<ArrayList<Order>> getOrdersHistory(int customerID) {
        return userRepo.getOrdersHistory(customerID);
    }
}
