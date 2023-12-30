package OrdersSystem.demo.Order.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import OrdersSystem.demo.Order.models.Order;
import OrdersSystem.demo.Order.models.OrderStatus;
import OrdersSystem.demo.Order.models.Product;
import org.springframework.stereotype.Component;

@Component
public class OrderRepo {
    private static OrderRepo instance;
    private ProductRepo productRepo;
    private ArrayList<ArrayList<Order>> orders = new ArrayList<>();

    public static OrderRepo getInstance() {
        if (instance == null) {
            instance = new OrderRepo();
        }
        return instance;
    }

    public OrderRepo() {
        productRepo = ProductRepo.getInstance();
        ArrayList<Product> products = productRepo.getProducts();
        Map<Product, Integer> productsOrder1 = new HashMap<>();
        productsOrder1.put(products.get(0), 2);
        productsOrder1.put(products.get(1), 3);
        productsOrder1.put(products.get(2), 1);

        ArrayList<Order> order1 = new ArrayList<>();
        order1.add(new Order(1, 20210018, "Cairo", OrderStatus.SHIPPED, 50, 100, 150, productsOrder1));
        orders.add(order1);

        Map<Product, Integer> productsOrder2 = new HashMap<>();
        productsOrder2.put(products.get(0), 3);
        productsOrder2.put(products.get(3), 1);
        productsOrder2.put(products.get(8), 5);
        ArrayList<Order> order2 = new ArrayList<>();
        order2.add(new Order(2, 20210161, "Alex", OrderStatus.SHIPPED, 50, 100, 150, productsOrder2));
        orders.add(order2);

        Map<Product, Integer> productsOrder3 = new HashMap<>();
        productsOrder3.put(products.get(2), 3);
        productsOrder3.put(products.get(7), 1);
        productsOrder3.put(products.get(8), 5);
        ArrayList<Order> order3 = new ArrayList<>();
        order3.add(new Order(3, 20210018, "Giza", OrderStatus.SHIPPED, 50, 100, 150, productsOrder3));
        orders.add(order3);

        Map<Product, Integer> productsOrder4 = new HashMap<>();
        productsOrder4.put(products.get(1), 3);
        productsOrder4.put(products.get(4), 1);
        productsOrder4.put(products.get(9), 5);
        ArrayList<Order> order4 = new ArrayList<>();
        order4.add(new Order(4, 20210018, "Aswan", OrderStatus.SHIPPED, 50, 100, 150, productsOrder4));
        orders.add(order4);
    }


    public ArrayList<ArrayList<Order>> getOrders() {
        return orders;
    }

    public ArrayList<ArrayList<Order>> getSimpleOrders() {
        ArrayList<ArrayList<Order>> simpleOrders = new ArrayList<>();
        for (ArrayList<Order> order : orders) {
            if (order.size() == 1) {
                simpleOrders.add(order);
            }
        }
        return simpleOrders;
    }

    public ArrayList<ArrayList<Order>> getCompoundOrders() {
        ArrayList<ArrayList<Order>> compoundOrders = new ArrayList<>();
        for (ArrayList<Order> order : orders) {
            if (order.size() > 1) {
                compoundOrders.add(order);
            }
        }
        return compoundOrders;
    }

    public Order getOrder(int orderID) {
        Order order = null;
        for (ArrayList<Order> orders : orders) {
            for (Order order1 : orders) {
                if (order1.getOrderID() == orderID) {
                    order = order1;
                    break;
                }
            }
        }
        return order;
    }


}
