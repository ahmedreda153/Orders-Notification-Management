package OrdersSystem.demo.Auth.controllers;

import OrdersSystem.demo.Auth.bsl.AuthenticationBsl;
import OrdersSystem.demo.Auth.bsl.IAuthenticationBsl;
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
    //API to login a user, take username and password as map in the body, return account manager object or null if not found
    @PostMapping (value = "/login")
    @Override
    public Object login(@RequestBody Map<String, String> body) {
        return iauthenticationBsl.login(body.get("email"), body.get("password"));
    }
    //API to register a new user, take user object in the body, return success or failure
    @PostMapping (value = "/register")
    @Override
    public String register(@RequestBody User user) {
        return iauthenticationBsl.register(user);
    }
    //API to get use order history, take customerID as path variable and return array of array of orders(array of array of orders to handle compound orders)
    @GetMapping (value = "/ordersHistory/{customerID}")
    @Override
    public ArrayList<ArrayList<Order>> getOrdersHistory(@PathVariable("customerID") int customerID) {
        return iauthenticationBsl.getOrdersHistory(customerID);
    }
}
