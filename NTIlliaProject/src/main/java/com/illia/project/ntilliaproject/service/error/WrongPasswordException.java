package com.illia.project.ntilliaproject.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WrongPasswordException extends RuntimeException{
    private WrongPasswordException(String message) {
        super(message);
    }

    public static ResponseStatusException create() {
        WrongPasswordException exception = new WrongPasswordException("Wrong password");
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}
