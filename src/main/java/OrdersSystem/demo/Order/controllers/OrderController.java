//package OrdersSystem.demo.Order.controllers;
//import OrdersSystem.demo.Order.models.Product;
//
//import OrdersSystem.demo.Order.bsl.OrderBsl;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class OrderController {
//    private OrderBsl orderbsl;
//
//
//    public void addProduct(Product product, int quantity) {
//        orderbsl.addProduct(product, quantity);
//    }
//
//    public void removeProduct(Product product) {
//        orderbsl.removeProduct(product);
//    }
//
//    public void calculateOrderPrice(){
//        orderbsl.calculateOrderPrice();
//    }
//
//    public void calculateTotalPrice(){
//        orderbsl.calculateTotalPrice();
//    }
//
//    public void setFees(double fees){
//        orderbsl.setFees(fees);
//    }
//
//    public double getFees(){
//        return orderbsl.getFees();
//    }
//
//
//
//}
