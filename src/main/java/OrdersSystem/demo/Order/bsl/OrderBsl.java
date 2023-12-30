package OrdersSystem.demo.Order.bsl;

import java.util.Map;
import OrdersSystem.demo.Order.models.Order;
import OrdersSystem.demo.Order.models.Product;
import org.springframework.stereotype.Service;

@Service
public class OrderBsl {
    private Order order;

    public OrderBsl() {
        this.order = new Order();
    }

    public void calculateFees(double fees) {
        order.setFees(fees);
    }

    public void addProduct(Product product, int quantity) {
        order.getOrderItems().put(product,quantity);
    }

    public void removeProduct(Product product) {
        order.getOrderItems().remove(product);
    }

    public void calculateOrderPrice() {
        double price = 0;
        for (Map.Entry<Product, Integer> entry : order.getOrderItems().entrySet()) {
            price += entry.getKey().getPrice() * entry.getValue();
        }
        order.setOrderPrice(price);
    }

    public void calculateTotalPrice(){
        double totalPrice = order.getOrderPrice()+order.getFees();
        order.setTotalPrice(totalPrice);
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

}
