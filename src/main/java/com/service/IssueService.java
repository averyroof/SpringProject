package com.service;

import com.entity.Book;
import com.entity.Issue;
import com.repository.IssueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IssueService {
    final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Transactional
    public List<Issue> findAll() {
        return (List<Issue>) issueRepository.findAll();
    }

    @Transactional
    public Issue findById(Long id) {
        return issueRepository.findById(id).get();
    }

//    @Transactional
//    public Book findByTitle(String title) {
//        return bookRepository.findByTitle(title);
//    }

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
}
