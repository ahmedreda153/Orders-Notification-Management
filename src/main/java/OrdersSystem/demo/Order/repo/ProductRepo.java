package OrdersSystem.demo.Order.repo;

import OrdersSystem.demo.Order.models.Product;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class ProductRepo {
    private static ProductRepo instance;
    private ArrayList<Product> products = new ArrayList<Product>();

    public static ProductRepo getInstance() {
        if (instance == null) {
            instance = new ProductRepo();
        }
        return instance;
    }

    public ProductRepo() {
        products.add(new Product("7895","Twix","Spinnes","Chocolate",1.50,10));
        products.add(new Product("7896","Snickers","Spinnes","Chocolate",2,20));
        products.add(new Product("7897","Mars","Spinnes","Chocolate",2.50,15));
        products.add(new Product("7898","Bounty","Spinnes","Chocolate",3.50,6));
        products.add(new Product("7899","Milky Way","Spinnes","Chocolate",4.50,32));
        products.add(new Product("7900","Kit Kat","Spinnes","Chocolate",5.0,12));
        products.add(new Product("7901","OrangeJuice","DinaFarms","Juice",6.50,10));
        products.add(new Product("7902","AppleJuice","DinaFarms","Juice",7.50,12));
        products.add(new Product("7903","MangoJuice","DinaFarms","Juice",8.50,11));
        products.add(new Product("7904","PeachJuice","DinaFarms","Juice",9.50,15));
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public Product getProduct(String productCode) {
        Product product = null;
        for (Product p : products) {
            if (p.getSerialNumber().equals(productCode)) {
                product = p;
            }
        }
        return product;
    }





}
