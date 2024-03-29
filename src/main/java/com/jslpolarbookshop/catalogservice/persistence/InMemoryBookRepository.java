package com.jslpolarbookshop.catalogservice.persistence;

import com.jslpolarbookshop.catalogservice.domain.Book;
import com.jslpolarbookshop.catalogservice.domain.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

//@Repository
public class InMemoryBookRepository  {
    private static final Map<String, Book> books = new ConcurrentHashMap<>();
//    @Override
    public Iterable<Book> findAll() {
        return books.values();
    }

//    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return existsByIsbn(isbn) ? Optional.of(books.get(isbn)) : Optional.empty();
    }

//    @Override
    public boolean existsByIsbn(String isbn) {
        return books.get(isbn) != null;
    }

//    @Override
    public Book save(Book book) {
        return books.put(book.isbn(), book);
    }

//    @Override
    public void deleteByIsbn(String isbn) {
        books.remove(isbn);
    }
}
