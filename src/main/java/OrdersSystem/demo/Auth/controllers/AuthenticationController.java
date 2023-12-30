package OrdersSystem.demo.Auth.controllers;

import OrdersSystem.demo.Auth.bsl.AuthenticationBsl;
import OrdersSystem.demo.Auth.bsl.IAuthenticationBsl;
import OrdersSystem.demo.Auth.models.AccountManager;
import OrdersSystem.demo.Auth.models.User;
import OrdersSystem.demo.Order.models.Order;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class AuthenticationController implements IAuthenticationController{
    private IAuthenticationBsl iauthenticationBsl;

    public AuthenticationController(){
        this.iauthenticationBsl = new AuthenticationBsl();
    }

    @PostMapping (value = "/login")
    @Override
    public AccountManager login(@RequestBody Map<String, String> body) {
        return iauthenticationBsl.login(body.get("username"), body.get("password"));
    }

//    @PostMapping (value = "/register")
//    @Override
//    public ArrayList<User> register(@RequestBody User user) {
//        iauthenticationBsl.register(user);
//        return iauthenticationBsl.allUsers();
//    }

    @PostMapping (value = "/register")
    @Override
    public String register(@RequestBody User user) {
        return iauthenticationBsl.register(user);
    }

    @GetMapping (value = "/ordersHistory/{customerID}")
    @Override
    public ArrayList<ArrayList<Order>> getOrdersHistory(@PathVariable("customerID") int customerID) {
        return iauthenticationBsl.getOrdersHistory(customerID);
    }
}
