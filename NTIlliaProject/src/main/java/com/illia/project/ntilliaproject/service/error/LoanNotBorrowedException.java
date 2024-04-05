package com.illia.project.ntilliaproject.service.error;

import com.illia.project.ntilliaproject.infrastructure.entity.LoanEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LoanNotBorrowedException extends RuntimeException{

    private LoanNotBorrowedException(String message) {
        super(message);
    }

    public static ResponseStatusException create(){
        LoanNotBorrowedException exception = new LoanNotBorrowedException("Loan not borrowed");
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}
