import java.util.ArrayList;

class CompoundOrder extends Order {
    private ArrayList<Order> orders;

    // public CompoundOrder() {
    //     super(orderID,address,fees, Map<Product, Integer> orderItems);
    //     orders = new ArrayList<Order>();
    // }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void calculateFees(double fees) {
        for (Order order : orders) {
            order.calculateFees(fees / orders.size());
        }
    }
}