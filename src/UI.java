import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UI {
    OrdersSystem ordersSystem;
    ProductDB productDB;
    ArrayList<Order> orders = new ArrayList<Order>();

    public UI() {
        ordersSystem = new OrdersSystem();
        productDB = new ProductDB();
    }

    public void displayMenu() {
        User user = new User(1, "Ahmed", "ahmed@gmail.com", "123456Aa", 500);
        AccountManager accountManager = new AccountManager(user, new ArrayList<Order>(), new ArrayList<Notification>());
        while (true) {
            System.out.println("Orders System");
            System.out.println("1. Add new order");
            System.out.println("2. Display all orders");
            System.out.println("3. Place order");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Choose order type");
                    System.out.println("1-Simple order");
                    System.out.println("2-Compound order");
                    int orderType = scanner.nextInt();
                    if (orderType == 1) {
                        Order order = addOrder();
                        accountManager.placeOrder(order);
                    } else if (orderType == 2) {
                        for (int i = 0; i < 2; i++) {
                            addOrder();
                        }
                    }
                    break;
                case 2:
                    displayAllOrders();
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    public Order addOrder() {
        Map<Product, Integer> products = new HashMap<Product, Integer>();
        Order order = new SimpleOrder();
        Scanner scanner = new Scanner(System.in);
        // System.out.println("Enter order ID");
        // String orderID = scanner.nextLine();
        // order.setOrderID(orderID);
        System.out.println("Enter address");
        String address = scanner.nextLine();
        order.setAddress(address);
        System.out.println("Choose your order items");
        productDB.displayAllProducts();
        while (true) {
            System.out.println("Enter product ID");
            String productID = scanner.nextLine();
            Product product = productDB.getProduct(productID);
            if (product == null) {
                System.out.println("Invalid product ID");
                continue;
            } else {
                System.out.println("Enter quantity");
                int quantity = scanner.nextInt();
                scanner.nextLine();
                order.addProduct(product, quantity);
            }

            System.out.println("Do you want to add another product? (y/n)");
            String choice = scanner.nextLine();
            if (choice.equals("n"))
                break;
        }
        orders.add(order);
        return order;
    }

    public void displayAllOrders() {
        for (Order orders : orders) {
            System.out.println(orders);
        }
    }
}