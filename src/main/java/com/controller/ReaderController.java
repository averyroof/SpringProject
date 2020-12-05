package com.controller;

import com.entity.Reader;
import com.service.ReaderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/readers")
public class ReaderController {

    final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping
    @ApiOperation(value = "Найти всех читателей")
    List<Reader> findAll() {
        List<Reader> readers = readerService.findAll();
        return readers;
    }

    @GetMapping("/filter")
    @ApiOperation(value = "Найти читателя по возрасту")
    List<Reader> findByAge(Integer start, Integer end) {
        return readerService.findByAge(start, end);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Найти читателя по id")
    Reader findById(@PathVariable Long id) {
        return readerService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Добавить читателя")
    Reader newReader(@RequestBody Reader newReader) {
        return readerService.create(newReader);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Обновить (в случае отсутствия id - сохранить) читателя")
    Reader saveOrUpdate(@RequestBody Reader newReader, @PathVariable Long id) {
        return readerService.updateOrCreate(id, newReader);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удалить читателя")
    void deleteReader(@PathVariable Long id) {
        readerService.deleteById(id);
    }
}
