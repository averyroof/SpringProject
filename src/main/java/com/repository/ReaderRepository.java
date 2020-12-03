package com.repository;

import com.entity.Reader;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReaderRepository extends CrudRepository<Reader, Long> {
    List<Reader> findByAgeBetween(Integer start, Integer end);
}
