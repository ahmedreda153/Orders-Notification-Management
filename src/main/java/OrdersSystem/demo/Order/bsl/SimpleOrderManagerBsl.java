package OrdersSystem.demo.Order.bsl;

import OrdersSystem.demo.Auth.bsl.AccountManagerBsl;
import OrdersSystem.demo.Order.models.Order;
import OrdersSystem.demo.Order.models.OrderStatus;
import OrdersSystem.demo.Order.repo.OrderRepo;

import java.util.ArrayList;
import java.util.Map;

public class SimpleOrderManagerBsl extends OrderManagerBsl {
    private OrderRepo orderRepo = OrderRepo.getInstance();

    public SimpleOrderManagerBsl() {
        super();
    }
    
    public void setOrderDetails(OrderBsl orderBsl, int customerID, String address) {
        Order order = orderBsl.getOrder();
        order.setOrderID(orderRepo.getOrders().size() + 1);
        order.setCustomerID(customerID);
        order.setAddress(address);
    }
    //calculate order price, fees and total price
    public void calculateOrderPrices(OrderBsl orderBsl) {
        orderBsl.calculateOrderPrice();
        orderBsl.calculateFees(30);
        orderBsl.calculateTotalPrice();
    }
    //finalize order and add it to orders list and orders history of the customer and deduct the balance
    public void finalizeOrder(OrderBsl orderBsl, AccountManagerBsl accountManager) {
        Order order = orderBsl.getOrder();
        order.setOrderStatus(OrderStatus.PLACED);
        ArrayList<Order> orderArrayList = new ArrayList<>();
        orderArrayList.add(order);
        orderRepo.getOrders().add(orderArrayList);
        accountManager.getAccountManager().getOrdersHistory().add(orderArrayList);
        accountManager.deductBalance(order.getOrderPrice());
    }
    //place order with products serial, customer id(s) and address(es) and return order if it is valid
    @Override
    public Object placeOrder(ArrayList<Map<String, Integer>> productsSerial, ArrayList<Integer> customerIDs, ArrayList<String> addresses) {
        // can't place simple order with more than one order
        if (customerIDs.size() > 1 || addresses.size() > 1 || productsSerial.size() > 1) {
            return "You can not place a simple order with more than one order";
        }
        return placeOrder(productsSerial.get(0), customerIDs.get(0), addresses.get(0));
    }

    @Override
    public ArrayList<ArrayList<Order>> getOrders() {
        return orderRepo.getSimpleOrders();
    }
}
