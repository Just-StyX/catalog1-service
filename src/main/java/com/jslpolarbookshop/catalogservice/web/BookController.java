package com.jslpolarbookshop.catalogservice.web;

import com.jslpolarbookshop.catalogservice.domain.Book;
import com.jslpolarbookshop.catalogservice.domain.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    public Iterable<Book> get() {
        return bookService.viewBookList();
    }
    @GetMapping("{isbn}")
    public Book getByIsbn(@PathVariable String isbn) {
        return bookService.viewBookDetails(isbn);
    }
    @PostMapping
    public ResponseEntity<Book> post(@Valid @RequestBody Book book) {
        var bbk = bookService.addBookToCatalog(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(bbk);
    }

    @DeleteMapping("{isbn}")
    public ResponseEntity<Void> delete(@PathVariable String isbn) {
        bookService.removeBookFromCatalog(isbn);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("{isbn}")
    public Book put(@PathVariable String isbn, @Valid @RequestBody Book book) {
        return bookService.editBookDetails(isbn, book);
    }
}
