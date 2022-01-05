package belajar.blibli.demo.service;

import belajar.blibli.demo.model.Product;

import java.util.Optional;

public interface ProductService {
    void saveUser(Product product);
    Optional<Product> getSingleProduct(String id);
}
