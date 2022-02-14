package com.library.service.impl;


import com.library.constant.CacheNames;
import com.library.model.Book;
import com.library.repository.BookCustomRepository;
import com.library.repository.BookRepository;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCustomRepository bookCustomRepository;

    @Override
    public void saveUser(String bookId,Book book) {
        Book existingProduct = bookCustomRepository.findItemByBookId(bookId);
        if(!Objects.isNull(existingProduct)){
         Book newBook = Book.builder().writer(book.getWriter()).publish(book.getPublish())
             .bookId(book.getBookId()).quantity(book.getQuantity())
             .title(book.getTitle()).totalPages(book.getTotalPages()).build();
         bookRepository.save(newBook);
        }
    }

    @Override
    @Cacheable(value = CacheNames.ITEM_ID, key = "#id")
    public Book getSingleProduct(String id) {
        return bookCustomRepository.findItemByBookId(id);
    }

    @Override
    public Page<Book> getAllProduct(Pageable pageable) {
        return bookCustomRepository.findALlWithPaging(pageable);
    }

    @Override
    @CachePut(value = CacheNames.ITEM_ID,key = "#id")
    public Book update(String id, Book newProduct) {
        Book existingBook = bookCustomRepository.findItemByBookId(id);
        if(Objects.isNull(existingBook)) {
            return null;
        };
        existingBook.setTitle(newProduct.getTitle());
        existingBook.setWriter(newProduct.getWriter());
        existingBook.setTotalPages(newProduct.getTotalPages());
        existingBook.setQuantity(newProduct.getQuantity());
        existingBook.setPublish(newProduct.getPublish());
        bookRepository.save(existingBook);
        return existingBook;
    }

    @Override
    public boolean deletBook(String id) {
        if(isBookExist(id)){
            return false;
        }
        return bookCustomRepository.deleteBookByBookId(id);
    }

    public boolean isBookExist(String id){
        return bookCustomRepository.findItemByBookId(id) != null;
    }
}
