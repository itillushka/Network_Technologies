package com.illia.project.ntilliaproject.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookISBNAlreadyExistsException extends RuntimeException{
    private BookISBNAlreadyExistsException(String message){
        super(message);
    }
    public static ResponseStatusException create(String isbn){
        BookISBNAlreadyExistsException exception = new BookISBNAlreadyExistsException("Book with isbn " + isbn + " already exists");
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}
