package com.controller;

import com.entity.Book;
import com.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/books")
public class BookController {

    final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @ApiOperation(value = "Найти все книги")
    List<Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/filter")
    @ApiOperation(value = "Найти книгу по названию")
    Book findBookByTitle(String title) {
        return bookService.findByTitle(title);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Найти книгу по id")
    Book findById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Добавить книгу")
    Book newBook(@RequestBody Book newBook) {
        return bookService.create(newBook);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Обновить (в случае отсутствия id - сохранить) книгу")
    Book saveOrUpdate(@RequestBody Book newBook, @PathVariable Long id) {
        return bookService.updateOrCreate(id, newBook);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удалить книгу")
    void deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
    }

}
