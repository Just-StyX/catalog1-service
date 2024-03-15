package com.jslpolarbookshop.catalogservice.web;

import com.jslpolarbookshop.catalogservice.domain.BookAlreadyExistsException;
import com.jslpolarbookshop.catalogservice.domain.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class BookControllerAdvice {
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> bookNotFoundHandler(BookNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<String> bookAlreadyExistsHandler(BookAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException exception) {
        var errors = new HashMap<String, String>();
        exception.getBindingResult().getAllErrors().forEach(
                error -> {
                    var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                }
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
