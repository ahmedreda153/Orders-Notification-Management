package OrdersSystem.demo.Product.bsl;

import OrdersSystem.demo.Order.models.Order;
import OrdersSystem.demo.Product.models.Product;

import java.util.ArrayList;
import java.util.Map;

public interface IProductBsl {
    ArrayList<Product> getAllProducts();
    void refundProductQuantities(Order order);
    void reduceProductQuantities(Map<String, Integer> productsSerial);
}
