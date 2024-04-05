package com.illia.project.ntilliaproject.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AlreadyExistsException extends RuntimeException{

    public AlreadyExistsException(String message) {
        super(message);
    }

    public static ResponseStatusException create(String entity){
        AlreadyExistsException exception = new AlreadyExistsException(entity + " already exists");
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}
