package com.illia.project.ntilliaproject.controller.dto.loan;

import java.util.Date;

public class CreateLoanResponseDto {

    private int loanID;

    private Date loanDate;

    private Date dueDate;

    private Date returnDate;

    public CreateLoanResponseDto(int loanID, Date loanDate, Date dueDate, Date returnDate) {
        this.loanID = loanID;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public CreateLoanResponseDto() {
    }

    public int getLoanID() {
        return loanID;
    }

    public void setLoanID(int loanID) {
        this.loanID = loanID;
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
