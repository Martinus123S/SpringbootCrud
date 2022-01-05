package belajar.blibli.demo.controller;

import belajar.blibli.demo.model.Product;
import belajar.blibli.demo.service.impl.ProductServiceImpl;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @GetMapping("/")
    public ResponseEntity<List<Product>> getProduct(){
        List<Product> productList= productService.getAllProduct();
        return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") String id,@RequestBody Product newProduct){
        try {
            Product product =  productService.update(id,newProduct);
            if(product == null){

                return new ResponseEntity<>("Data Tidak Ditemukan",HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSingleProduct(@PathVariable("id") String id){
        try{
            Optional<Product> singleProduct = productService.getSingleProduct(id);
            if(singleProduct == null){
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
