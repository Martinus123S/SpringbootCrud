package belajar.blibli.demo.service;

import belajar.blibli.demo.model.Product;
import belajar.blibli.demo.request.ProductRequest;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void saveUser(Product product);
    Optional<Product> getSingleProduct(String id);
    List<Product> getAllProduct();
    Product update(String id, ProductRequest product);
}
