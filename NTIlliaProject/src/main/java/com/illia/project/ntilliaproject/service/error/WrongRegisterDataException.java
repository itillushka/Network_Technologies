package com.illia.project.ntilliaproject.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WrongRegisterDataException extends RuntimeException{

        private WrongRegisterDataException(String message) {
            super(message);
        }
        public static ResponseStatusException create(){
            WrongRegisterDataException exception = new WrongRegisterDataException("Wrong role for user is provided, options are ROLE_READER or ROLE_ADMIN");
            return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
        }
}
