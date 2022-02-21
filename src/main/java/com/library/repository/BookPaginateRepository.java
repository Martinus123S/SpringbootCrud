package com.library.repository;

import com.library.model.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookPaginateRepository extends PagingAndSortingRepository<Book,String> {

}
