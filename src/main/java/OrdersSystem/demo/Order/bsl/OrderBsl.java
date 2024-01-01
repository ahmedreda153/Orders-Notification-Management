package OrdersSystem.demo.Order.bsl;

import java.util.Map;

import OrdersSystem.demo.Auth.models.AccountManager;
import OrdersSystem.demo.Order.models.Order;
import OrdersSystem.demo.Order.models.OrderStatus;
import OrdersSystem.demo.Product.models.Product;
import OrdersSystem.demo.Product.repo.ProductRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class OrderBsl {
    @Setter
    @Getter
    private Order order;
    private ProductRepo productRepo;

    public OrderBsl() {
        this.order = new Order();
        this.productRepo = ProductRepo.getInstance();
    }
    
    public void calculateFees(double fees) {
        order.setFees(fees);
    }
    //add product to order within the same order
    public void addProduct(Product product, int quantity) {
        order.getOrderItems().put(product,quantity);
    }
    //adding the whole order of products to the order product by product
    public String addProductsToOrder(OrderBsl orderBsl, Map<String, Integer> productsSerial) {
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
    //calculate and setting order price without fees
    public void calculateOrderPrice() {
        double price = 0;
        for (Map.Entry<Product, Integer> entry : order.getOrderItems().entrySet()) {
            price += entry.getKey().getPrice() * entry.getValue();
        }
        order.setOrderPrice(price);
    }
    //calculate and setting total price with fees
    public void calculateTotalPrice(){
        double totalPrice = order.getOrderPrice()+order.getFees();
        order.setTotalPrice(totalPrice);
    }
    //check if order is valid
    public static String checkOrderValidations(Order order, AccountManager accountManager) {
        if (order == null) {
            return "Order not found";
        }
        if (accountManager == null) {
            return "Customer not found";
        }
        if (accountManager.getId() != order.getCustomerID()) {
            return "Customer is not the owner of the order";
        }
        if (order.getOrderStatus() == OrderStatus.SHIPPED) {
            return "Order is already shipped";
        } else if (order.getOrderStatus() == OrderStatus.CANCELLED) {
            return "Order is cancelled";
        }
        return null;
    }
}
