package belajar.blibli.demo.service;

import belajar.blibli.demo.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void saveUser(Product product);
    Optional<Product> getSingleProduct(String id);
    List<Product> getAllProduct();
    Product update(String id,Product product);
}
