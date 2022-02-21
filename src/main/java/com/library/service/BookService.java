package com.library.service;

import com.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

public interface BookService {
    void saveBook(String bookdId, Book book);
    Book getSingleProduct(String id);
    Page<Book> getAllProduct(int page, int size);
    Book update(String id, Book book);
    boolean deletBook(String id);
    void saveBulkBooks(MultipartFile multipartFile) throws IOException, ParseException;
}
