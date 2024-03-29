package com.illia.project.ntilliaproject.controller.dto.loan;

import java.util.Date;

public class CreateLoanDto {

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    private int bookID;


    private Date loanDate;

    private Date dueDate;

    private Date returnDate;

    public CreateLoanDto(int bookID, Date loanDate, Date dueDate, Date returnDate) {
        this.bookID = bookID;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;

    }

    public CreateLoanDto() {

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

}
