package OrdersSystem.demo.Order.controllers;

import OrdersSystem.demo.Order.bsl.ProductBsl;
import OrdersSystem.demo.Order.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ProductController {
    private ProductBsl productBsl;

    public ProductController() {
        this.productBsl = new ProductBsl();
    }

    @GetMapping(value = "/products")
    public ArrayList<Product> getAllProducts() {
        return productBsl.getAllProducts();
    }
}
