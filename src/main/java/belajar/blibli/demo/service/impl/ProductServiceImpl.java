package belajar.blibli.demo.service.impl;


import belajar.blibli.demo.model.Product;
import belajar.blibli.demo.repository.ProductRepository;
import belajar.blibli.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;


    @Override
    public void saveUser(Product product) {
        productRepository.save(product);
    }

    @Override
    public Optional<Product> getSingleProduct(String id) {
        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()){
            return null;
        }
        return product;
    }

}
