package com.illia.project.ntilliaproject.controller.dto.loan;

import java.util.Date;

public class ReturnLoanDto {

    private int bookId;

    private Date returnDate;

    public ReturnLoanDto(int bookId, Date returnDate) {
        this.bookId = bookId;
        this.returnDate = returnDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
