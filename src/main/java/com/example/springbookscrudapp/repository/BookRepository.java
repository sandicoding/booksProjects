package com.example.springbookscrudapp.repository;


import com.example.springbookscrudapp.model.Book;
import org.springframework.data.repository.CrudRepository;


/**
 * ''
 */
public interface BookRepository extends CrudRepository<Book, Long> {

    Book save(long id, Book book);

    String delete(long id);
}
