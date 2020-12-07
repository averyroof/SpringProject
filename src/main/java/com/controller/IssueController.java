package com.controller;

import com.entity.Issue;
import com.service.IssueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/issues")
public class IssueController {
    final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping
    @ApiOperation(value = "Найти все выдачи")
    List<Issue> findAll() {
        return issueService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Найти выдачу по id")
    Issue findById(@PathVariable Long id) {
        return issueService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Добавить выдачу")
    Issue newIssue(@RequestBody Issue newIssue) {
        return issueService.create(newIssue);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Обновить (в случае отсутствия id - сохранить) выдачу")
    Issue saveOrUpdate(@RequestBody Issue newIssue, @PathVariable Long id) {
        return issueService.updateOrCreate(id, newIssue);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удалить выдачу")
    void deleteIssue(@PathVariable Long id) {
        issueService.deleteById(id);
    }

    @PutMapping("/addReader")
    @ApiOperation(value = "Установить читателя для выдачи") // ?
    Issue addReaderToIssue(@RequestParam Long id_issue, @RequestParam Long id_reader) {
        return issueService.addReaderToIssue(id_issue, id_reader);
    }

    @PutMapping("/addBook")
    @ApiOperation(value = "Установить книгу для выдачи")
    Issue addBookToIssue(@RequestParam Long id_issue, @RequestParam Long id_book) {
        return issueService.addBookToIssue(id_issue, id_book);
    }

    @GetMapping("/findActive")
    @ApiOperation(value = "Найти действительные выдачи")
    List<String> findActive() {
        return issueService.findReadersWithActiveIssue();
    }
}
