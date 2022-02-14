package com.library.controller;

import com.library.model.Book;
import com.library.constant.MessageCategory;
import com.library.request.ResponseCustom;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;


    @GetMapping("/books")
    public ResponseEntity<Page<Book>> getProduct( @RequestParam(required = false) Integer page,
        @RequestParam(required = false) Integer size){
        Page<Book> productList= bookService.getAllProduct(PageRequest.of(page,size));
        return ResponseEntity.ok(productList);
    }

    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id")String id){
        try{
            if(!bookService.deletBook(id)){
                return ResponseCustom.generateResponse(MessageCategory.MESSAGE_NOT_FOUND,HttpStatus.OK,null);
            }
            return ResponseCustom.generateResponse(MessageCategory.MESSAGE_DELETE,HttpStatus.OK,null);
        }catch (Exception e){
            return ResponseCustom.generateResponse(MessageCategory.MESSAGE_ERROR,HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
    @PutMapping("/update-book/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") String id,
        @RequestBody Book newProduct){
        try {
            Book book =  bookService.update(id,newProduct);
            if(book == null){

                return ResponseCustom.generateResponse(MessageCategory.MESSAGE_NOT_FOUND,HttpStatus.OK,null);
            }
            return ResponseCustom.generateResponse(MessageCategory.MESSAGE_UPDATE,HttpStatus.OK,book);
        }catch (Exception e){
            return ResponseCustom.generateResponse(MessageCategory.MESSAGE_ERROR,HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<?> getSingleProduct(@PathVariable("id") String id){
        try{
            Book singleBook = bookService.getSingleProduct(id);

            if(singleBook == null){
                return ResponseCustom.generateResponse(MessageCategory.MESSAGE_NOT_FOUND,HttpStatus.OK,null);
            }
            return ResponseCustom.generateResponse(MessageCategory.MESSAGE_SUCCESS,HttpStatus.OK,singleBook);
        }catch (Exception e){
            return ResponseCustom.generateResponse(MessageCategory.MESSAGE_ERROR,HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
    @PostMapping("/book")
    public ResponseEntity<?> saveProduct(@RequestBody Book newBook){
        try{
            String bookId = newBook.getBookId();
            bookService.saveUser(bookId,newBook);
            return ResponseCustom.generateResponse(MessageCategory.MESSAGE_INSERT,HttpStatus.OK,newBook);
        }catch (Exception e){
            return ResponseCustom.generateResponse(MessageCategory.MESSAGE_ERROR,HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
}
