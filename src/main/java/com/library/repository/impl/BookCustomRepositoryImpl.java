package com.library.repository.impl;

import com.library.model.Book;
import com.library.repository.BookCustomRepository;
import com.library.repository.BookPaginateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class BookCustomRepositoryImpl implements BookCustomRepository {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Autowired
  private BookPaginateRepository bookPaginateRepository;

  @Override
  public Book findItemByBookId(String id) {
    Query query = new Query(where("bookId").is(id));
    return mongoTemplate.findOne(query,Book.class);
  }

  @Override
  public Page<Book> findALlWithPaging(int page, int size) {
    Pageable paging = PageRequest.of(page,size);
    Page<Book> bookPagedResult = bookPaginateRepository.findAll(paging);
    return bookPagedResult;
  }

  @Override
  public boolean deleteBookByBookId(String id) {
    Query query = new Query();
    Criteria criteria = new Criteria();
    query.addCriteria(criteria.and("bookId").is(id));
    mongoTemplate.remove(query,Book.class);
    Book book = mongoTemplate.findOne(query,Book.class);
    return book == null;
  }

}
