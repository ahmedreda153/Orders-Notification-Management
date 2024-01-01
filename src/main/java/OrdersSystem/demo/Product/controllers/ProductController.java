package OrdersSystem.demo.Product.controllers;

import OrdersSystem.demo.Product.bsl.IProductBsl;
import OrdersSystem.demo.Product.bsl.ProductBsl;
import OrdersSystem.demo.Product.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ProductController implements IProductController{
    private IProductBsl productBsl;

    public ProductController() {
        this.productBsl = new ProductBsl();
    }
    //API to get all products from the database
    @GetMapping(value = "/products")
    public ArrayList<Product> getAllProducts() {
        return productBsl.getAllProducts();
    }
}
