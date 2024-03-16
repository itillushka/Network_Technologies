package com.illia.project.ntilliaproject.controller;

import com.illia.project.ntilliaproject.controller.dto.loan.CreateLoanDto;
import com.illia.project.ntilliaproject.controller.dto.loan.CreateLoanResponseDto;
import com.illia.project.ntilliaproject.controller.dto.loan.GetLoanDto;
import com.illia.project.ntilliaproject.infrastructure.entity.LoanEntity;
import com.illia.project.ntilliaproject.infrastructure.entity.ReviewEntity;
import com.illia.project.ntilliaproject.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public List<GetLoanDto> getAllLoans(){
        return loanService.getAll();
    }

    @GetMapping("/{id}")
    public GetLoanDto getOne(@PathVariable long id){
        return loanService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<CreateLoanResponseDto> create(@RequestBody CreateLoanDto loan){
        var newLoan = loanService.create(loan);
        return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        loanService.delete(id);
        return ResponseEntity.noContent().build();
    }
}