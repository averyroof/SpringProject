package com.repository;

import com.entity.Book;
import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends CrudRepository<Book, Long> {
    Book findByTitle(String title);
}
