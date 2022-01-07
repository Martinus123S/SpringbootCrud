package belajar.blibli.demo.service.impl;


import belajar.blibli.demo.model.Product;
import belajar.blibli.demo.repository.ProductRepository;
import belajar.blibli.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
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

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product update(String id,Product newProduct) {
        Optional<Product> singleProduct = getSingleProduct(id);
        if(singleProduct == null){
            return null;
        }
        Product existProduct = singleProduct.get();
        existProduct.setExpired(newProduct.getExpired());
        existProduct.setDescription(newProduct.getDescription());
        existProduct.setPrice(newProduct.getPrice());
        existProduct.setName(newProduct.getName());
        existProduct.setQuantity(newProduct.getQuantity());
        productRepository.save(existProduct);
        return existProduct;
    }

}
