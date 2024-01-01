package OrdersSystem.demo.Order.controllers;

import OrdersSystem.demo.Order.bsl.CompoundOrderManagerBsl;
import OrdersSystem.demo.Order.bsl.OrderManagerBsl;
import OrdersSystem.demo.Order.bsl.SimpleOrderManagerBsl;
import OrdersSystem.demo.Order.models.Order;
import OrdersSystem.demo.Order.models.OrderCriteria;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderManagerController implements IOrderManagerController {
    private OrderManagerBsl makeSimpleOrderBsl;
    private OrderManagerBsl makeCompoundOrderBsl;

    public OrderManagerController() {
        this.makeSimpleOrderBsl = new SimpleOrderManagerBsl();
        this.makeCompoundOrderBsl = new CompoundOrderManagerBsl();
    }

    // API to place an order for a customer with a single product type and quantity
    // (simple order)
    @PostMapping(value = "/placeOrder")
    public Object placeSimpleOrder(@RequestBody OrderCriteria orderCriteria) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < orderCriteria.getProducts().size(); i++) {
            map.put(orderCriteria.getProducts().get(i).getSerialNumber(),
                    orderCriteria.getProducts().get(i).getQuantity());
        }
        return makeSimpleOrderBsl.placeOrder(map, orderCriteria.getCustomerID(), orderCriteria.getAddress());
    }

    // API to place compound order
    @PostMapping(value = "/placeCompoundOrder")
    public Object placeCompoundOrder(@RequestBody ArrayList<OrderCriteria> orderCriterias) {
        ArrayList<Map<String, Integer>> productsMaps = new ArrayList<>();
        ArrayList<Integer> customerIDs = new ArrayList<>();
        ArrayList<String> addresses = new ArrayList<>();
        for (int i = 0; i < orderCriterias.size(); i++) {
            Map<String, Integer> map = new HashMap<>();
            for (int j = 0; j < orderCriterias.get(i).getProducts().size(); j++) {
                map.put(orderCriterias.get(i).getProducts().get(j).getSerialNumber(),
                        orderCriterias.get(i).getProducts().get(j).getQuantity());
            }
            productsMaps.add(map);
            customerIDs.add(orderCriterias.get(i).getCustomerID());
            addresses.add(orderCriterias.get(i).getAddress());
        }
        return makeCompoundOrderBsl.placeOrder(productsMaps, customerIDs, addresses);
    }

    // API to get all orders
    @GetMapping(value = "/getAllOrders")
    public ArrayList<ArrayList<Order>> getOrders() {
        return makeSimpleOrderBsl.getAllOrders();
    }

    // API to get all simple orders
    @GetMapping(value = "/getSimpleOrders")
    public ArrayList<ArrayList<Order>> getSimpleOrders() {
        return makeSimpleOrderBsl.getOrders();
    }

    // API to get all compound orders
    @GetMapping(value = "/getCompoundOrders")
    public ArrayList<ArrayList<Order>> getCompoundOrders() {
        return makeCompoundOrderBsl.getOrders();
    }

    // API to ship an order
    @PostMapping(value = "/shipOrder")
    public Object shipOrder(@RequestBody Map<String, String> body) {
        int orderID = Integer.parseInt(body.get("orderID"));
        int customerID = Integer.parseInt(body.get("customerID"));
        return makeSimpleOrderBsl.shipOrder(orderID, customerID);
    }

    // API to cancel an order
    @PostMapping(value = "/cancelOrder")
    public Object cancelOrder(@RequestBody Map<String, String> body) {
        int orderID = Integer.parseInt(body.get("orderID"));
        int customerID = Integer.parseInt(body.get("customerID"));
        return makeSimpleOrderBsl.cancelOrder(orderID, customerID);
    }
}
