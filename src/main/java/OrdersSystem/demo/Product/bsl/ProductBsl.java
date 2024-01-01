package OrdersSystem.demo.Product.bsl;

import OrdersSystem.demo.Order.models.Order;
import OrdersSystem.demo.Product.models.Product;
import OrdersSystem.demo.Product.repo.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class ProductBsl implements IProductBsl{
    private ProductRepo productRepo;

    public ProductBsl() {
        this.productRepo = new ProductRepo();
    }

    public ArrayList<Product> getAllProducts() {
        return productRepo.getProducts();
    }
    //returns the product quantity when cancel order
    public void refundProductQuantities(Order order) {
        for (Map.Entry<Product, Integer> entry : order.getOrderItems().entrySet()) {
            Product product = entry.getKey();
            product.setQuantity(product.getQuantity() + entry.getValue());
        }
    }
    //reduce the product quantity when place order
    public void reduceProductQuantities(Map<String, Integer> productsSerial) {
        for (Map.Entry<String, Integer> entry : productsSerial.entrySet()) {
            Product product = productRepo.getProduct(entry.getKey());
            product.setQuantity(product.getQuantity() - entry.getValue());
        }
    }
}
