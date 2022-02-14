package com.library.repository.impl;

import com.library.model.Book;
import com.library.repository.BookCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCustomRepositoryImpl implements BookCustomRepository {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public Book findItemByBookId(String id) {
    Query query = new Query();
    query.addCriteria(new Criteria("bookId").is(id));
    return mongoTemplate.findOne(query,Book.class);
  }

  @Override
  public Page<Book> findALlWithPaging(Pageable pageable) {
    Query query = new Query();
    query.skip(pageable.getPageNumber());
    query.limit(pageable.getPageSize());
    List<Book> listAll = mongoTemplate.find(query,Book.class);
    long total = listAll.size();
    return new PageImpl<>(listAll,pageable,total);
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
