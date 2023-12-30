package OrdersSystem.demo.Order.bsl;

import OrdersSystem.demo.Auth.models.AccountManager;
import OrdersSystem.demo.Auth.repo.UsersRepo;
import OrdersSystem.demo.Order.repo.OrderRepo;
import OrdersSystem.demo.Auth.bsl.AccountManagerBsl;
import OrdersSystem.demo.Order.models.Order;
import OrdersSystem.demo.Order.models.OrderStatus;

import java.util.ArrayList;
import java.util.Map;

public class MakeCompoundOrderBsl extends MakeOrderBsl{
    private CompoundOrderBsl compoundOrderBsl;
    private int numOfCustomers;
    private OrderRepo orderRepo = OrderRepo.getInstance();
    private UsersRepo usersRepo = UsersRepo.getInstance();

    public void setOrderDetails(OrderBsl orderBsl, int customerID, String address) {
        Order order = orderBsl.getOrder();
        order.setOrderID(orderRepo.getOrders().size() + compoundOrderBsl.getOrders().size() + 1);
        order.setCustomerID(customerID);
        order.setAddress(address);
    }

    @Override
    public void calculateOrderPrices(OrderBsl orderBsl) {
        orderBsl.calculateOrderPrice();
        int fees = 60 / numOfCustomers;
        orderBsl.calculateFees(fees);
        orderBsl.calculateTotalPrice();
    }

    @Override
    public void finalizeOrder(OrderBsl orderBsl, AccountManagerBsl accountManager) {
        Order order = orderBsl.getOrder();
        order.setOrderStatus(OrderStatus.PLACED);
        accountManager.deductBalance(order.getOrderPrice());
    }

    @Override
    public ArrayList<ArrayList<Order>> getOrders() {
        return orderRepo.getCompoundOrders();
    }

    public Object placeOrder(ArrayList<Map<String, Integer>> productsSerial, ArrayList<Integer> customerIDs, ArrayList<String> addresses) {
        compoundOrderBsl = new CompoundOrderBsl();
        numOfCustomers = customerIDs.size();
//        compoundOrderBsl.setOrders(new ArrayList<>(productsSerial.size()));
        for (int i = 0; i < productsSerial.size(); i++) {
            compoundOrderBsl.addOrder((OrderBsl) placeOrder(productsSerial.get(i), customerIDs.get(i), addresses.get(i)));
        }
        ArrayList<Order> orderArrayList = new ArrayList<>();
        for (OrderBsl orderBsl : compoundOrderBsl.getOrders()) {
            orderArrayList.add(orderBsl.getOrder());
        }
        orderRepo.getOrders().add(orderArrayList);
        AccountManager accountManager = usersRepo.getAccountManager(customerIDs.get(0));
        AccountManagerBsl accountManagerBsl = new AccountManagerBsl(accountManager);
        accountManagerBsl.getAccountManager().getOrdersHistory().add(orderArrayList);
        return compoundOrderBsl.getOrders();
    }
}
