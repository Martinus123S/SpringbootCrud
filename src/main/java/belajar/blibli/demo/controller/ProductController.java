package belajar.blibli.demo.controller;

import belajar.blibli.demo.model.Product;
import belajar.blibli.demo.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @GetMapping("/")
    public String getProduct(){
        return "Hello World";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSingleProduct(@PathVariable("id") String id){
        try{
            Optional<Product> singleProduct = productService.getSingleProduct(id);
            if(!singleProduct.isPresent()){
                return new ResponseEntity<String>("Data TIdak ada",HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Product>(singleProduct.get(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/product")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        try{
            productService.saveUser(product);
            return new ResponseEntity<Product>(product,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }
}
