package com.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;

    private LocalDateTime dateCreation;

    private Long countBooks;

    private Long countReaders;

    private Long countIssues;

    public Statistics() {
    }

    public Statistics(LocalDateTime dateCreation, Long countBooks, Long countReaders, Long countIssues) {
        this.dateCreation = dateCreation;
        this.countBooks = countBooks;
        this.countIssues = countIssues;
        this.countReaders = countReaders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Long getCountBooks() {
        return countBooks;
    }

    public void setCountBooks(Long countBooks) {
        this.countBooks = countBooks;
    }

    public Long getCountReaders() {
        return countReaders;
    }

    public void setCountReaders(Long countReaders) {
        this.countReaders = countReaders;
    }

    public Long getCountIssues() {
        return countIssues;
    }

    public void setCountIssues(Long countIssues) {
        this.countIssues = countIssues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistics that = (Statistics) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(dateCreation, that.dateCreation) &&
                Objects.equals(countBooks, that.countBooks) &&
                Objects.equals(countReaders, that.countReaders) &&
                Objects.equals(countIssues, that.countIssues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateCreation, countBooks, countReaders, countIssues);
    }
}
