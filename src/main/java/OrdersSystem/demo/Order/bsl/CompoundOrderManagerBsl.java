package OrdersSystem.demo.Order.bsl;

import OrdersSystem.demo.Auth.models.AccountManager;
import OrdersSystem.demo.Auth.repo.UsersRepo;
import OrdersSystem.demo.Order.repo.OrderRepo;
import OrdersSystem.demo.Auth.bsl.AccountManagerBsl;
import OrdersSystem.demo.Order.models.Order;
import OrdersSystem.demo.Order.models.OrderStatus;

import java.util.ArrayList;
import java.util.Map;

public class CompoundOrderManagerBsl extends OrderManagerBsl {
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
    //calculate the order price and fees and total price
    @Override
    public void calculateOrderPrices(OrderBsl orderBsl) {
        orderBsl.calculateOrderPrice();
        //divide the total fees by the number of customers
        int fees = 30 / numOfCustomers;
        orderBsl.calculateFees(fees);
        orderBsl.calculateTotalPrice();
    }
    //finalize the order and set its status and deduct the balance of the customer
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
    //place the compound order with the products, customerIDs, addresses and return the orders
    public Object placeOrder(ArrayList<Map<String, Integer>> productsSerial, ArrayList<Integer> customerIDs, ArrayList<String> addresses) {
        // can't place compound order with less or more than 3 orders
        if (productsSerial.size() != 3 || customerIDs.size() != 3 || addresses.size() != 3) {
            return "You can not place a compound order with less or more than 3 orders";
        }

        //check if the customers are in the same governorate to place a compound order,
        //if not the same the costumer can not place a compound order
        String address = addresses.get(0).split(",")[1].trim();
        for (String a: addresses) {
            if (!a.split(",")[1].trim().equals(address)) {
                return "Addresses are not the same, Customers should be in the same governorate";
            }
        }
        compoundOrderBsl = new CompoundOrderBsl();
        numOfCustomers = customerIDs.size();
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
