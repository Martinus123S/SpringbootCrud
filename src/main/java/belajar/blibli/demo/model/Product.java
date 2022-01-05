package belajar.blibli.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("product")
public class Product {
    @Id
    private String productId;

    private String name;
    private int quantity;
    private double price;
    private String description;
    private Date expired;
}
