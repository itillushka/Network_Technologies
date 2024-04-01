package com.illia.project.ntilliaproject.controller.dto.loan;

import java.util.Date;

public class ReturnLoanDto {

    private int loanId;

    private Date returnDate;

    public ReturnLoanDto(int loanId, Date returnDate) {
        this.loanId = loanId;
        this.returnDate = returnDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }
}
