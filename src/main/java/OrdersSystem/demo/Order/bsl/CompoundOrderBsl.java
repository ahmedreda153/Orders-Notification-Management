package OrdersSystem.demo.Order.bsl;

import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class CompoundOrderBsl extends OrderBsl{
    private ArrayList<OrderBsl> orders;

    public CompoundOrderBsl() {
        orders = new ArrayList<>();
    }
    //add order to compound order
    public void addOrder(OrderBsl order) {
        orders.add(order);
    }
    //remove order from compound order
    public void removeOrder(OrderBsl order) {
        orders.remove(order);
    }
    //calculate fees for each order in compound order
    @Override
    public void calculateFees(double fees) {
        for (OrderBsl order : orders) {
            order.calculateFees(fees / orders.size());
        }
    }
    public ArrayList<OrderBsl> getOrders() {
        return orders;
    }
    public void setOrders(ArrayList<OrderBsl> orders) {
        this.orders = orders;
    }

}
