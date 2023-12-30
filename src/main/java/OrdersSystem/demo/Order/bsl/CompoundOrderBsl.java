package OrdersSystem.demo.Order.bsl;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CompoundOrderBsl extends OrderBsl{
    private ArrayList<OrderBsl> orders;
    public CompoundOrderBsl() {
        orders = new ArrayList<>();
    }

    public void addOrder(OrderBsl order) {
        orders.add(order);
    }

    public void removeOrder(OrderBsl order) {
        orders.remove(order);
    }

    public ArrayList<OrderBsl> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<OrderBsl> orders) {
        this.orders = orders;
    }

    @Override
    public void calculateFees(double fees) {
        for (OrderBsl order : orders) {
            order.calculateFees(fees / orders.size());
        }
    }
}
