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
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCustomRepository bookCustomRepository;

    @Override
    public void saveBook(String bookId,Book book) {
        Book existingProduct = bookCustomRepository.findItemByBookId(bookId);
        if(!Objects.isNull(existingProduct)){
         Book newBook = Book.builder().writer(book.getWriter())
             .description(book.getDescription())
             .bookId(book.getBookId())
             .quantity(book.getQuantity())
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
    public Page<Book> getAllProduct(int page, int size) {
        return bookCustomRepository.findALlWithPaging(page,size);
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
        existingBook.setDescription(newProduct.getDescription());
        bookRepository.save(existingBook);
        return existingBook;
    }

    @Override
    public boolean deletBook(String id) {
        if(isBookExist(id)){
            return bookCustomRepository.deleteBookByBookId(id);
        }
        return false;
    }

    @Override
    public void saveBulkBooks(MultipartFile multipartFile) throws IOException, ParseException {
        List<String> rows = getContent(multipartFile);
        for (String eachRow : rows) {
            List<String> content = Arrays.asList(eachRow.split(";"));
            String bookId = content.get(0);
            String title = content.get(1);
            String description = content.get(2);
            Integer totalPages = Integer.valueOf(content.get(3));
            Integer quantity = Integer.valueOf(content.get(4));
            String writer = content.get(5);
            if(!(isBookExist(bookId))){
                bookRepository.save(new Book(bookId,title,description,totalPages,quantity,writer));
            }
        }
    }


    public List<String> getContent(MultipartFile multipartFile) throws IOException {
        List<String> result = new ArrayList<>();
        String line;
        InputStreamReader streamReader =
            new InputStreamReader(multipartFile.getInputStream(), StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        while ((line = bufferedReader.readLine()) != null) {
            result.add(line);
        }
        return result;
    }

    public boolean isBookExist(String id){
        return bookCustomRepository.findItemByBookId(id) != null;
    }
}
