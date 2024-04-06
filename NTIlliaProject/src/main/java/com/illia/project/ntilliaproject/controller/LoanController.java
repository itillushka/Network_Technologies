package com.illia.project.ntilliaproject.controller;

import com.illia.project.ntilliaproject.controller.dto.loan.*;
import com.illia.project.ntilliaproject.service.LoanService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/all")
    public List<GetLoanDto> getAllLoans(HttpServletRequest request){

        //get token from header
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        //remove Bearer from token
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        return loanService.getAll(token);
    }

    @GetMapping("/{id}")
    public GetLoanDto getOne(@PathVariable long id){
        return loanService.getOne(id);
    }

    @PostMapping("/newLoan")
    public ResponseEntity<CreateLoanResponseDto> create(@RequestBody CreateLoanDto loan,HttpServletRequest request){

        //get token from header
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        //remove Bearer from token
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        //create new loan
        var newLoan = loanService.create(loan, token);
        return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
    }

    @PostMapping("/{loanId}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UpdateStatusResponseDto> updateStatus(@RequestBody UpdateStatusDto updateStatusDto, @PathVariable String loanId){

        var loanIdLong = Long.parseLong(loanId.substring(1, loanId.length() - 1));

        var updatedLoan = loanService.updateStatus(updateStatusDto, loanIdLong);
        return new ResponseEntity<>(updatedLoan, HttpStatus.OK);
    }

    @PostMapping("/return")
    public ResponseEntity<ReturnLoanResponseDto> returnLoan(@RequestBody ReturnLoanDto returnLoanDto, HttpServletRequest request){

        //get token from header
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        //remove Bearer from token
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        System.out.println(token);

        var returnedLoan = loanService.returnLoan(returnLoanDto, token);
        System.out.println("Processed");
        return new ResponseEntity<>(returnedLoan, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        loanService.delete(id);
        return ResponseEntity.noContent().build();
    }
}