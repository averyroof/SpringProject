package com.service;

import com.entity.Book;
import com.entity.Reader;
import com.repository.ReaderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReaderService {
    final ReaderRepository readerRepository;

    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @Transactional
    public List<Reader> findAll() {
        return (List<Reader>) readerRepository.findAll();
    }

    @Transactional
    public Reader findById(Long id) {
        return readerRepository.findById(id).get();
    }

    @Transactional
    public List<Reader> findByAge(Integer start, Integer end) {
        return readerRepository.findByAgeBetween(start, end);
    }

    @Transactional
    public Reader create(Reader newReader) {
        return readerRepository.save(newReader);
    }

    @Transactional
    public Reader updateOrCreate(Long id, Reader newReader) {
        return readerRepository.findById(id)
                .map(x -> {
                    x.setFullName(newReader.getFullName());
                    x.setAge(newReader.getAge());
                    return readerRepository.save(x);
                })
                .orElseGet(() -> {
                    newReader.setId(id);
                    return readerRepository.save(newReader);
                });
    }

    @Transactional
    public void deleteById(Long id) {
        readerRepository.deleteById(id);
    }
}
