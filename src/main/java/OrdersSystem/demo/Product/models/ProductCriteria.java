package OrdersSystem.demo.Product.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCriteria {//this class is used to be able to send a product to the body of a request
    private String serialNumber;
    private int quantity;
}
