package com.illia.project.ntilliaproject.controller.dto.loan;

import com.illia.project.ntilliaproject.commonTypes.LoanStatus;

public class UpdateStatusDto {

    private int loanId;

    private LoanStatus status;

    public UpdateStatusDto(int loanId, LoanStatus status) {
        this.loanId = loanId;
        this.status = status;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }
}
