package com.illia.project.ntilliaproject.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoBooksAvailableException extends RuntimeException{

        public NoBooksAvailableException(String message) {
            super(message);
        }

        public static ResponseStatusException create(){
            NoBooksAvailableException exception = new NoBooksAvailableException("No copies of the book are available");
            return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
        }
}
