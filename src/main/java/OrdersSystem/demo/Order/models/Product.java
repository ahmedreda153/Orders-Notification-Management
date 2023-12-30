package OrdersSystem.demo.Order.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "serialNumber")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String serialNumber;
    private String name;
    private String vendor;
    private String category;
    private double price;
    private int quantity;

    @Override
    public String toString() {
        return "Product {" +
                "serialNumber='" + serialNumber + '\'' +
                ", name='" + name + '\'' +
                ", vendor='" + vendor + '\'' +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
