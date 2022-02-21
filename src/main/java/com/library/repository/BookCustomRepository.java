package com.library.repository;

import com.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookCustomRepository {
  Book findItemByBookId(String id);
  boolean deleteBookByBookId(String id);
  Page<Book> findALlWithPaging(int page, int size);
}
