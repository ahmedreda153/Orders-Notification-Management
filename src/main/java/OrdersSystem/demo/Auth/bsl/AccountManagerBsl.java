package OrdersSystem.demo.Auth.bsl;

import OrdersSystem.demo.Auth.models.AccountManager;
import OrdersSystem.demo.Order.models.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class AccountManagerBsl {
    private AccountManager accountManager;

    public AccountManagerBsl(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void deductBalance(double amount) {
        accountManager.getUser().setBalance(accountManager.getUser().getBalance() - amount);
    }

}
