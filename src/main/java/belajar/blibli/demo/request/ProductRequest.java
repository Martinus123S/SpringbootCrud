package belajar.blibli.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

  private String name;
  private int quantity;
  private double price;
  private String description;
  private Date expired;
}
