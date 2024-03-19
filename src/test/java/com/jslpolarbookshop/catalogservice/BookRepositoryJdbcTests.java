package com.jslpolarbookshop.catalogservice;

import com.jslpolarbookshop.catalogservice.config.DataConfig;
import com.jslpolarbookshop.catalogservice.domain.Book;
import com.jslpolarbookshop.catalogservice.domain.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
@ActiveProfiles("integration")
public class BookRepositoryJdbcTests {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;
    @Test
    void findBookByIsbnWhenExisting() {
        var isbn = "1234567892";
        var book = Book.of(isbn, "Title", "Author", 12.90, "Princeton");
        jdbcAggregateTemplate.insert(book);
        Optional<Book> actualBook = bookRepository.findByIsbn(isbn);

        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().isbn()).isEqualTo(book.isbn());
    }
}
