package com.illia.project.ntilliaproject.controller.dto.loan;

import java.util.Date;

public class CreateLoanResponseDto {

    private int loanID;

    private int userID;

    private Date loanDate;

    private Date dueDate;


    public CreateLoanResponseDto(int loanID, int userID, Date loanDate, Date dueDate) {
        this.loanID = loanID;
        this.userID = userID;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
