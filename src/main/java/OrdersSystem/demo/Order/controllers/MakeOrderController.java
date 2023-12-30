package OrdersSystem.demo.Order.controllers;

import OrdersSystem.demo.Order.bsl.MakeCompoundOrderBsl;
import OrdersSystem.demo.Order.bsl.MakeOrderBsl;
import OrdersSystem.demo.Order.bsl.MakeSimpleOrderBsl;
import OrdersSystem.demo.Order.models.Order;
import OrdersSystem.demo.Order.models.OrderCriteria;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MakeOrderController {
    private MakeOrderBsl makeSimpleOrderBsl;
    private MakeOrderBsl makeCompoundOrderBsl;

    public MakeOrderController() {
        this.makeSimpleOrderBsl = new MakeSimpleOrderBsl();
        this.makeCompoundOrderBsl = new MakeCompoundOrderBsl();
    }

    @PostMapping (value = "/placeOrder")
    public Object placeOrder(@RequestBody OrderCriteria orderCriteria) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < orderCriteria.getProducts().size(); i++) {
            map.put(orderCriteria.getProducts().get(i).getSerialNumber(), orderCriteria.getProducts().get(i).getQuantity());
        }
        return makeSimpleOrderBsl.placeOrder(map, orderCriteria.getCustomerID(), orderCriteria.getAddress());
    }

    @PostMapping(value = "/placeCompoundOrder")
    public Object placeCompoundOrder(@RequestBody ArrayList<OrderCriteria> orderCriterias) {
        ArrayList<Map<String, Integer>> productsMaps = new ArrayList<>();
        ArrayList<Integer> customerIDs = new ArrayList<>();
        ArrayList<String> addresses = new ArrayList<>();
        for (int i = 0; i < orderCriterias.size(); i++) {
            Map<String, Integer> map = new HashMap<>();
            for (int j = 0; j < orderCriterias.get(i).getProducts().size(); j++) {
                map.put(orderCriterias.get(i).getProducts().get(j).getSerialNumber(), orderCriterias.get(i).getProducts().get(j).getQuantity());
            }
            productsMaps.add(map);
            customerIDs.add(orderCriterias.get(i).getCustomerID());
            addresses.add(orderCriterias.get(i).getAddress());
        }
        return makeCompoundOrderBsl.placeOrder(productsMaps, customerIDs, addresses);
    }

    @GetMapping (value = "/getAllOrders")
    public ArrayList<ArrayList<Order>> getOrders() {
        return makeSimpleOrderBsl.getAllOrders();
    }

    @GetMapping (value = "/getSimpleOrders")
    public ArrayList<ArrayList<Order>> getSimpleOrders() {
        return makeSimpleOrderBsl.getOrders();
    }

    @GetMapping (value = "/getCompoundOrders")
    public ArrayList<ArrayList<Order>> getCompoundOrders() {
        return makeCompoundOrderBsl.getOrders();
    }

    @PostMapping (value = "/shipOrder")
    public Object shipOrder(@RequestBody Map<String, String> body) {
        int orderID = Integer.parseInt(body.get("orderID"));
        int customerID = Integer.parseInt(body.get("customerID"));
        return makeSimpleOrderBsl.shipOrder(orderID, customerID);
    }
}
