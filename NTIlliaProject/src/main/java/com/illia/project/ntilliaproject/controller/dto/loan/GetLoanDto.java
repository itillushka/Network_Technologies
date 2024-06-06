package com.illia.project.ntilliaproject.controller.dto.loan;


import com.illia.project.ntilliaproject.commonTypes.LoanStatus;

import java.util.Date;

public class GetLoanDto {

    private int loanID;

    private int bookID;

    private Date loanDate;

    private Date dueDate;

    private Date returnDate;

    private LoanStatus status;

    public GetLoanDto(int loanID, int bookID, Date loanDate, Date dueDate, Date returnDate, LoanStatus status) {
        this.loanID = loanID;
        this.bookID = bookID;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public GetLoanDto() {
    }

    public int getLoanID() {
        return loanID;
    }

    public void setLoanID(int loanID) {
        this.loanID = loanID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }
}
