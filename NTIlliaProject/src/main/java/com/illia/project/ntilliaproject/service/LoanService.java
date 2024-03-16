package com.illia.project.ntilliaproject.service;

import com.illia.project.ntilliaproject.controller.dto.loan.CreateLoanDto;
import com.illia.project.ntilliaproject.controller.dto.loan.CreateLoanResponseDto;
import com.illia.project.ntilliaproject.controller.dto.loan.GetLoanDto;
import com.illia.project.ntilliaproject.infrastructure.entity.LoanEntity;
import com.illia.project.ntilliaproject.infrastructure.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    // dependency injection
    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }
    public GetLoanDto getOne(long id){
        var loanEntity= loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan not found"));
        return new GetLoanDto(
                loanEntity.getLoanID(),
                loanEntity.getLoanDate(),
                loanEntity.getDueDate(),
                loanEntity.getReturnDate());

    }
    public List<GetLoanDto> getAll(){
        var loans= loanRepository.findAll();
        return loans.stream().map((loan)-> new GetLoanDto(
                loan.getLoanID(),
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getReturnDate())).collect(Collectors.toList());
    }

    public CreateLoanResponseDto create(CreateLoanDto loan){
        var loanEntity = new LoanEntity();
        loanEntity.setLoanDate(loan.getLoanDate());
        loanEntity.setDueDate(loan.getDueDate());
        loanEntity.setReturnDate(loan.getReturnDate());
        var newLoan= loanRepository.save(loanEntity);
        return new CreateLoanResponseDto(newLoan.getLoanID(),
                newLoan.getLoanDate(),
                newLoan.getDueDate(),
                newLoan.getReturnDate());
    }

    public void delete(long id){
        if(!loanRepository.existsById(id)){
            throw new RuntimeException("Loan not found");
        }
        loanRepository.deleteById(id);
    }
}
