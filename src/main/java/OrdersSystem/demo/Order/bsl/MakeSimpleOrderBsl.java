package OrdersSystem.demo.Order.bsl;

import OrdersSystem.demo.Auth.bsl.AccountManagerBsl;
import OrdersSystem.demo.Order.models.Order;
import OrdersSystem.demo.Order.models.OrderStatus;
import OrdersSystem.demo.Order.repo.OrderRepo;

import java.util.ArrayList;
import java.util.Map;

public class MakeSimpleOrderBsl extends MakeOrderBsl{
    private OrderRepo orderRepo = OrderRepo.getInstance();

    public MakeSimpleOrderBsl() {
        super();
    }

    public void setOrderDetails(OrderBsl orderBsl, int customerID, String address) {
        Order order = orderBsl.getOrder();
        order.setOrderID(orderRepo.getOrders().size() + 1);
        order.setCustomerID(customerID);
        order.setAddress(address);
    }

    public void calculateOrderPrices(OrderBsl orderBsl) {
        orderBsl.calculateOrderPrice();
        orderBsl.calculateFees(40);
        orderBsl.calculateTotalPrice();
    }

    public void finalizeOrder(OrderBsl orderBsl, AccountManagerBsl accountManager) {
        Order order = orderBsl.getOrder();
        order.setOrderStatus(OrderStatus.PLACED);
        ArrayList<Order> orderArrayList = new ArrayList<>();
        orderArrayList.add(order);
        orderRepo.getOrders().add(orderArrayList);
        accountManager.getAccountManager().getOrdersHistory().add(orderArrayList);
        accountManager.deductBalance(order.getOrderPrice());
    }

    @Override
    public ArrayList<ArrayList<Order>> getOrders() {
        return orderRepo.getSimpleOrders();
    }

    @Override
    public Object placeOrder(ArrayList<Map<String, Integer>> productsSerial, ArrayList<Integer> customerIDs, ArrayList<String> addresses) {
        return placeOrder(productsSerial.get(0), customerIDs.get(0), addresses.get(0));
    }
}
