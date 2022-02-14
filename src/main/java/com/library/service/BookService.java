package com.library.service;

import com.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    void saveUser(String bookdId, Book book);
    Book getSingleProduct(String id);
    Page<Book> getAllProduct(Pageable pageable);
    Book update(String id, Book book);
    boolean deletBook(String id);
}
