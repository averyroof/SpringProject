package com;

import com.entity.Book;
import com.entity.Issue;
import com.entity.Reader;
import com.repository.BookRepository;
import com.repository.IssueRepository;
import com.repository.ReaderRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.text.SimpleDateFormat;

@SpringBootApplication
//@EnableAspectJAutoProxy(proxyTargetClass=true)
//@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    final ReaderRepository readerRepository;
    final BookRepository bookRepository;
    final IssueRepository issueRepository;

    public Application(ReaderRepository readerRepository, BookRepository bookRepository, IssueRepository issueRepository) {
        this.readerRepository = readerRepository;
        this.bookRepository = bookRepository;
        this.issueRepository = issueRepository;
    }

    @Bean
    @Profile("dev")
    public ApplicationRunner devApplicationRunner() {
        return arg -> {
            Reader reader1 = readerRepository.save(new Reader("Иванов Иван Иванович", 25));
            Reader reader2 = readerRepository.save(new Reader("Буданов Савелий Ермолаевич", 14));
            Reader reader3 = readerRepository.save(new Reader("Степанишин Тарас Андронович", 36));
            Reader reader4 = readerRepository.save(new Reader("Янечко Аристарх Федотович", 11));

            Book book1 = bookRepository.save(new Book("Три товарища", 1936));
            Book book2 = bookRepository.save(new Book("Белый Клык", 1906));
            Book book3 = bookRepository.save(new Book("Мартин Иден", 1909));
            Book book4 = bookRepository.save(new Book("Фауст", 1774));

            Issue issue1 = issueRepository.save(new Issue(formatter.parse("2020-12-02"), formatter.parse("2020-12-25"), reader1, book1));
            Issue issue2 = issueRepository.save(new Issue(formatter.parse("2020-11-22"), formatter.parse("2020-12-02"), reader2, book3));
            Issue issue3 = issueRepository.save(new Issue(formatter.parse("2020-12-01"), formatter.parse("2021-01-10"), reader2, book4));
            Issue issue4 = issueRepository.save(new Issue(formatter.parse("2020-10-15"), formatter.parse("2020-11-17"), reader3, book3));
        };
    }
}
