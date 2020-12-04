package com.repository;

import com.entity.Issue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Tuple;
import java.util.List;

public interface IssueRepository extends CrudRepository<Issue, Long> {
    @Query(value = "SELECT r.id as readerId, r.full_name as readerName, i.id as issueId, i.date_of_issue as dateIssue, " +
            "i.date_delivery as dateDelivery FROM reader r INNER JOIN issue i ON i.reader_id = r.id " +
            "WHERE i.date_of_issue <= CURRENT_TIMESTAMP AND i.date_delivery > CURRENT_TIMESTAMP",
            nativeQuery = true)
    List<Tuple> findReadersWithActiveIssue();
}
