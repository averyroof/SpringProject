package com.service;

import com.entity.Issue;
import com.repository.BookRepository;
import com.repository.IssueRepository;
import com.repository.ReaderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueService {
    final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    final IssueRepository issueRepository;
    final ReaderRepository readerRepository;
    final BookRepository bookRepository;

    public IssueService(IssueRepository issueRepository, ReaderRepository readerRepository, BookRepository bookRepository) {
        this.issueRepository = issueRepository;
        this.readerRepository = readerRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public List<Issue> findAll() {
        return (List<Issue>) issueRepository.findAll();
    }

    @Transactional
    public Issue findById(Long id) {
        return issueRepository.findById(id).get();
    }

    @Transactional
    public Issue create(Issue newIssue) {
        return issueRepository.save(newIssue);
    }

    @Transactional
    public Issue updateOrCreate(Long id, Issue newIssue) {
        return issueRepository.findById(id)
                .map(x -> {
                    x.setDateOfIssue(newIssue.getDateOfIssue());
                    x.setDateDelivery(newIssue.getDateDelivery());
                    return issueRepository.save(x);
                })
                .orElseGet(() -> {
                    newIssue.setId(id);
                    return issueRepository.save(newIssue);
                });
    }

    @Transactional
    public void deleteById(Long id) {
        issueRepository.deleteById(id);
    }

    @Transactional
    public Issue addReaderToIssue(Long id_issue, Long id_reader) {
        return issueRepository.findById(id_issue)
                .map(x -> {
                    x.setReader(readerRepository.findById(id_reader).get());
                    return issueRepository.save(x);
                }).get();
    }

    @Transactional
    public Issue addBookToIssue(Long id_issue, Long id_book) {
        return issueRepository.findById(id_issue)
                .map(x -> {
                    x.setBook(bookRepository.findById(id_book).get());
                    return issueRepository.save(x);
                }).get();
    }

    @Transactional
    public List<String> findReadersWithActiveIssue() {
        return issueRepository.findReadersWithActiveIssue().stream().map(t -> "ФИО: " +
                t.get("readerName", String.class) + ";  Дата выдачи: " +
                formatter.format(t.get("dateIssue", Date.class)) + ";  Дата сдачи: " +
                formatter.format(t.get("dateDelivery", Date.class))).collect(Collectors.toList());
    }
}
