package com.service;

import com.entity.Statistics;
import com.repository.BookRepository;
import com.repository.IssueRepository;
import com.repository.ReaderRepository;
import com.repository.StatisticsRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class StatisticsService {
    final StatisticsRepository statisticsRepository;
    final BookRepository bookRepository;
    final IssueRepository issueRepository;
    final ReaderRepository readerRepository;

    public StatisticsService(StatisticsRepository statisticsRepository, BookRepository bookRepository, IssueRepository issueRepository, ReaderRepository readerRepository) {
        this.statisticsRepository = statisticsRepository;
        this.bookRepository = bookRepository;
        this.issueRepository = issueRepository;
        this.readerRepository = readerRepository;
    }

    @Scheduled(fixedDelay = 60000) //  в минуту
    @Transactional
    public void addStatistics() {
        System.out.println("СЕЙЧАС ДОБАВИТСЯ");
        statisticsRepository.save(new Statistics(LocalDateTime.now(), bookRepository.count(), readerRepository.count(), issueRepository.count()));
        System.out.println("ДОБАВИЛОСЬ");
    }
}
