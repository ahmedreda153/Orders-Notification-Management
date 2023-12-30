package OrdersSystem.demo.Order.bsl;

import OrdersSystem.demo.Order.models.Product;
import OrdersSystem.demo.Order.repo.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductBsl {
    private ProductRepo productRepo;

    public ProductBsl() {
        this.productRepo = new ProductRepo();
    }

    public ArrayList<Product> getAllProducts() {
        return productRepo.getProducts();
    }
}
