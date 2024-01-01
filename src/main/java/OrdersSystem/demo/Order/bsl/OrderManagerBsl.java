package OrdersSystem.demo.Order.bsl;

import OrdersSystem.demo.Auth.bsl.AccountManagerBsl;
import OrdersSystem.demo.Auth.models.AccountManager;
import OrdersSystem.demo.Auth.repo.UsersRepo;
import OrdersSystem.demo.Notfications.bsl.*;
import OrdersSystem.demo.Order.models.Order;
import OrdersSystem.demo.Order.models.OrderStatus;
import OrdersSystem.demo.Order.repo.OrderRepo;
import OrdersSystem.demo.Product.bsl.IProductBsl;
import OrdersSystem.demo.Product.bsl.ProductBsl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

@Service
public abstract class OrderManagerBsl {
    private OrderRepo orderRepo;
    private UsersRepo usersRepo;
    private IProductBsl productBsl;
    NotificationTemplateBsl notificationTemplateBsl;

    public OrderManagerBsl() {
        this.orderRepo = OrderRepo.getInstance();
        this.usersRepo = UsersRepo.getInstance();
        this.productBsl = new ProductBsl();
    }

    //place order by products serial and customer id and address
    public Object placeOrder(Map<String, Integer> productsSerial, int customerID, String address) {
        AccountManager accountManager = usersRepo.getAccountManager(customerID);
        if (accountManager == null) {
            return "Customer not found";
        }
        AccountManagerBsl accountManagerBsl = new AccountManagerBsl(accountManager);
        double balance = accountManager.getUser().getBalance();
        OrderBsl orderBsl = new OrderBsl();
        String productsAdded = orderBsl.addProductsToOrder(orderBsl, productsSerial);
        if (productsAdded != null) {
            return productsAdded;
        }
        setOrderDetails(orderBsl, customerID, address);
        calculateOrderPrices(orderBsl);
        if (balance < orderBsl.getOrder().getTotalPrice()) {
            return "Not enough balance";
        }
        finalizeOrder(orderBsl, accountManagerBsl);
        productBsl.reduceProductQuantities(productsSerial);
        notificationTemplateBsl = new ConfirmationTemplateBsl();
        notify(orderBsl.getOrder(), accountManager, 1, notificationTemplateBsl);
        return orderBsl;
    }

    public abstract void setOrderDetails(OrderBsl orderBsl, int customerID, String address);
    //calculate order prices
    public abstract void calculateOrderPrices(OrderBsl orderBsl);
    //finalize order
    public abstract void finalizeOrder(OrderBsl orderBsl, AccountManagerBsl accountManager);
    //notify customer
    public void notify(Order order, AccountManager accountManager, int id, NotificationTemplateBsl notificationTemplateBsl) {
        INotificationManagerBsl notificationManagerBsl = new NotificationManagerBsl();
        notificationManagerBsl.addAccountManager(accountManager);
        notificationManagerBsl.addToQueue(order, id, notificationTemplateBsl);
    }

    public ArrayList<ArrayList<Order>> getAllOrders() {
        return orderRepo.getOrders();
    }
    public abstract ArrayList<ArrayList<Order>> getOrders();

    public abstract Object placeOrder(ArrayList<Map<String, Integer>> productsSerial, ArrayList<Integer> customerIDs, ArrayList<String> addresses);

    //shipping order by order id and customer id and reduce balance
    public Object shipOrder(int orderID, int customerID) {
        Order order = orderRepo.getOrder(orderID);
        AccountManager accountManager = usersRepo.getAccountManager(customerID);
        String validation = OrderBsl.checkOrderValidations(order, accountManager);
        if (validation != null) {
            return validation;
        }
        order.setOrderStatus(OrderStatus.SHIPPED);
        notificationTemplateBsl = new ShippingTemplateBsl();
        notify(order, accountManager, 2, notificationTemplateBsl);
        AccountManagerBsl accountManagerBsl = new AccountManagerBsl(accountManager);
        accountManagerBsl.deductBalance(order.getFees());
        return order;
    }
    //cancel order by order id and customer id within 2 days of placing it
    public Object cancelOrder(int orderID, int customerID) {
        ArrayList<Order> orderList = orderRepo.getOrderList(orderID);
        Order order = orderRepo.getOrder(orderID);
        AccountManager accountManager = usersRepo.getAccountManager(customerID);
        String validation = OrderBsl.checkOrderValidations(order, accountManager);
        if (validation != null) {
            return validation;
        }
        if (orderList.get(0).getOrderID() != orderID) {
            return "Order is a compound order, only who placed the order can cancel it.";
        }
        LocalDateTime orderDate = LocalDateTime.parse(order.getOrderDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(orderDate.plusDays(2))) {
            return "Order can't be cancelled after 2 days of placing it.";
        }
        notificationTemplateBsl = new CancellationTemplateBsl();
        for (Order o : orderList) {
            o.setOrderStatus(OrderStatus.CANCELLED);
            int CID = o.getCustomerID();
            AccountManager accManager = usersRepo.getAccountManager(CID);
            notify(o, accManager, 3, notificationTemplateBsl);
            AccountManagerBsl accountManagerBsl = new AccountManagerBsl(accManager);
            accountManagerBsl.addBalance(o.getOrderPrice());
            productBsl.refundProductQuantities(o);
        }
        return orderList;
    }

}
