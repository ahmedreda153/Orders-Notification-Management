package OrdersSystem.demo.Order.bsl;


import OrdersSystem.demo.Auth.bsl.AccountManagerBsl;
import OrdersSystem.demo.Auth.models.AccountManager;
import OrdersSystem.demo.Auth.repo.UsersRepo;
import OrdersSystem.demo.Order.models.Order;
import OrdersSystem.demo.Order.models.OrderStatus;
import OrdersSystem.demo.Order.models.Product;
import OrdersSystem.demo.Order.repo.OrderRepo;
import OrdersSystem.demo.Order.repo.ProductRepo;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Map;

@Service
public abstract class MakeOrderBsl {
    private OrderRepo orderRepo;
    private ProductRepo productRepo;
    private UsersRepo usersRepo;

    public MakeOrderBsl() {
        this.orderRepo = OrderRepo.getInstance();
        this.productRepo = ProductRepo.getInstance();
        this.usersRepo = UsersRepo.getInstance();
    }
    public Object placeOrder(Map<String, Integer> productsSerial, int customerID, String address) {
        AccountManager accountManager = usersRepo.getAccountManager(customerID);
        if (accountManager == null) {
            return "Customer not found";
        }
        AccountManagerBsl accountManagerBsl = new AccountManagerBsl(accountManager);
        double balance = accountManager.getUser().getBalance();
        OrderBsl orderBsl = new OrderBsl();
        String productsAdded = addProductsToOrder(orderBsl, productsSerial);
        if (productsAdded != null) {
            return productsAdded;
        }
        setOrderDetails(orderBsl, customerID, address);
        calculateOrderPrices(orderBsl);
        if (balance < orderBsl.getOrder().getTotalPrice()) {
            return "Not enough balance";
        }
        finalizeOrder(orderBsl, accountManagerBsl);
        updateProductQuantities(productsSerial);
        return orderBsl;
    }

    private String addProductsToOrder(OrderBsl orderBsl, Map<String, Integer> productsSerial) {
        for (Map.Entry<String, Integer> entry : productsSerial.entrySet()) {
            Product product = productRepo.getProduct(entry.getKey());
            if (product == null) {
                return "Product " + entry.getKey() + " not found";
            }
            if (product.getQuantity() < entry.getValue()) {
                return "Product " + entry.getKey() + " quantity is not enough";
            }
            orderBsl.addProduct(product, entry.getValue());
        }
        return null;
    }

    public abstract void setOrderDetails(OrderBsl orderBsl, int customerID, String address);

    public abstract void calculateOrderPrices(OrderBsl orderBsl);

    public abstract void finalizeOrder(OrderBsl orderBsl, AccountManagerBsl accountManager);

    private void updateProductQuantities(Map<String, Integer> productsSerial) {
        for (Map.Entry<String, Integer> entry : productsSerial.entrySet()) {
            Product product = productRepo.getProduct(entry.getKey());
            product.setQuantity(product.getQuantity() - entry.getValue());
        }
    }

    public ArrayList<ArrayList<Order>> getAllOrders() {
        return orderRepo.getOrders();
    }
    public abstract ArrayList<ArrayList<Order>> getOrders();



    public abstract Object placeOrder(ArrayList<Map<String, Integer>> productsSerial, ArrayList<Integer> customerIDs, ArrayList<String> addresses);

    public Object shipOrder(int orderID, int customerID) {
        Order order = orderRepo.getOrder(orderID);
        if (order == null) {
            return "Order not found";
        }
        AccountManager accountManager = usersRepo.getAccountManager(customerID);
        if (accountManager == null) {
            return "Customer not found";
        }
        if (accountManager.getId() != order.getCustomerID()) {
            return "Customer is not the owner of the order";
        }
        if (order.getOrderStatus() != OrderStatus.PLACED) {
            if (order.getOrderStatus() == OrderStatus.SHIPPED) {
                return "Order is already shipped";
            }
            return "Order is not placed";
        }
        order.setOrderStatus(OrderStatus.SHIPPED);
        AccountManagerBsl accountManagerBsl = new AccountManagerBsl(accountManager);
        accountManagerBsl.deductBalance(order.getFees());
        return order;
    }
}
