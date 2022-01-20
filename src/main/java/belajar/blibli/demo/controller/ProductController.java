package belajar.blibli.demo.controller;

import belajar.blibli.demo.model.Product;
import belajar.blibli.demo.request.ProductRequest;
import belajar.blibli.demo.request.ResponseCustom;
import belajar.blibli.demo.service.impl.ProductServiceImpl;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @GetMapping("/product")
    @Cacheable(value = "product")
    public ResponseCustom getProduct(){
        List<Product> productList= productService.getAllProduct();

        return new ResponseCustom(productList,HttpStatus.OK);
    }

    @DeleteMapping("/delete-product/{id}")
    @CacheEvict(value = "product",key = "#id")
    public ResponseEntity<?> deleteProduct(@PathVariable("id")String id){
        return null;
    }

    @PutMapping("/update/{id}")
    @CachePut(value = "product",key = "#id")
    public ResponseCustom updateProduct(@PathVariable("id") String id,
        @RequestBody ProductRequest newProduct){
        try {
            Product product =  productService.update(id,newProduct);
            if(product == null){

                return new ResponseCustom("Data Tidak Ditemukan",HttpStatus.NOT_FOUND);
            }
            return new ResponseCustom(product, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseCustom(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product/{id}")
    @Cacheable(value = "product",key = "#id")
    public ResponseEntity<?> getSingleProduct(@PathVariable("id") String id){
        try{
            Optional<Product> singleProduct = productService.getSingleProduct(id);

            String cachedValue = redisTemplate.opsForValue().get("product:61e50287c5468c405bf77df4");
            System.out.println(cachedValue);

            if(singleProduct == null){
                return new ResponseEntity<String>("Data TIdak ada",HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Product>(singleProduct.get(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/product")
    public ResponseCustom saveProduct(@RequestBody Product product){
        try{
            productService.saveUser(product);
            return new ResponseCustom(product,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseCustom(HttpStatus.NOT_FOUND);
        }
    }
}
