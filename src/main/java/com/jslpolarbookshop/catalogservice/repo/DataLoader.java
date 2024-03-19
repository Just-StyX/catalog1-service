package com.jslpolarbookshop.catalogservice.repo;

import com.jslpolarbookshop.catalogservice.domain.Book;
import com.jslpolarbookshop.catalogservice.domain.BookRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//@Profile("testData") /** use this or the conditional on property annotation as below */
@ConditionalOnProperty(name = "polar.testData.enabled", havingValue = "true")
public class DataLoader {
    private final BookRepository bookRepository;
    public DataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @EventListener(ApplicationReadyEvent.class)
    public void loadTestData() {
        bookRepository.deleteAll();
        var book1 = Book.of("1234567891", "Northern Lights", "Tyson Fury", 9.9, "Harvard");
        var book2 = Book.of("1234567892", "Polar Journey", "Michael Jordan", 12.9, "Princeton");
        bookRepository.saveAll(List.of(book1, book2));
    }
}
