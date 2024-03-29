package OrdersSystem.demo.Order.models;

import OrdersSystem.demo.Product.models.ProductCriteria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCriteria {
    private Integer customerID;
    private String address;
    private ArrayList<ProductCriteria> products;
}