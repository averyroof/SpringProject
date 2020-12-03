package com.service;

import com.entity.Book;
import com.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {
    final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public List<Book> findAll() {
        return (List<Book>) bookRepository.findAll();
    }

    @Transactional
    public Book findById(Long id) {
        return bookRepository.findById(id).get();
    }

    @Transactional
    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Transactional
    public Book create(Book newBook) {
        return bookRepository.save(newBook);
    }

    @Transactional
    public Book updateOrCreate(Long id, Book newBook) {
        return bookRepository.findById(id)
                .map(x -> {
                    x.setTitle(newBook.getTitle());
                    x.setYear(newBook.getYear());
                    return bookRepository.save(x);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return bookRepository.save(newBook);
                });
    }

    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
