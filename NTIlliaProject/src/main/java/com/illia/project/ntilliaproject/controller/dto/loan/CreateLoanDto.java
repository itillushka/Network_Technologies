package com.illia.project.ntilliaproject.controller.dto.loan;

import java.util.Date;

public class CreateLoanDto {
    private int bookID;

    private Date loanDate;

    private Date dueDate;

    public CreateLoanDto(int bookID, Date loanDate, Date dueDate) {
        this.bookID = bookID;
        this.loanDate = loanDate;
        this.dueDate = dueDate;

    }

    public CreateLoanDto() {

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


}
