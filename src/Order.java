import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Order
 */
public abstract class Order {
    private String orderID;
    private String address;
    private double fees;
    private double orderPrice;
    private double totalPrice;
    private Map<Product, Integer> orderItems= new HashMap<Product, Integer>();

    // private ArrayList<Product> orderItems = new ArrayList<Product>();

    // public Order(String orderID, String address, double fees, Map<Product, Integer> orderItems) {
    //     this.orderID = orderID;
    //     this.address = address;
    //     this.fees = fees;
    //     this.orderItems = orderItems;
    // }

    public abstract void calculateFees(double fees);
    
    public String getOrderID() {
        return this.orderID;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void addProduct(Product product, int quantity) {
        this.orderItems.put(product, quantity);
    }

    public void removeProduct(Product product) {
        this.orderItems.remove(product);
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public double getFees() {
        return this.fees;
    }
    
    public void calculateOrderPrice() {
        orderPrice = 0;
        for (Map.Entry<Product, Integer> entry : orderItems.entrySet()) {
            orderPrice += entry.getKey().getPrice() * entry.getValue();
        }
    }
    
    public double getOrderPrice() {
        return this.orderPrice;
    }

    public void calculateTotalPrice() {
        this.totalPrice = this.getOrderPrice() + this.getFees();
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void printOrderItems() {
        for (Map.Entry<Product, Integer> entry : orderItems.entrySet()) {
            System.out.println(entry.getKey().getName() + " " + entry.getValue());
        }
    }

    public String toString() {
        return "Order ID: " + this.orderID + "\nAddress: " + this.address + "\nFees: " + this.fees + "\nTotal Price: " + this.totalPrice;
    }

}