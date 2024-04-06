package com.illia.project.ntilliaproject.controller;

import com.illia.project.ntilliaproject.controller.dto.loan.*;
import com.illia.project.ntilliaproject.infrastructure.suplementary.ProcessToken;
import com.illia.project.ntilliaproject.service.LoanService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is the controller class for loans.
 * It handles HTTP requests related to loans.
 */
@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private final LoanService loanService;

    private final ProcessToken processToken;

    /**
     * Constructor for the LoanController class.
     * @param loanService the service that this controller will use to process loans
     * @param processToken the utility that this controller will use to process tokens
     */
    @Autowired
    public LoanController(LoanService loanService, ProcessToken processToken) {
        this.loanService = loanService;
        this.processToken = processToken;
    }

    /**
     * Method to get all loans.
     * @return List<GetLoanDto> the list of DTOs containing the loans
     */
    @GetMapping("/all")
    public List<GetLoanDto> getAllLoans(HttpServletRequest request){

        String token = processToken.getToken(request);

        return loanService.getAll(token);
    }

    /**
     * Method to get a single loan by its ID.
     * @param id the ID of the loan
     * @return GetLoanDto the DTO containing the loan
     */
    @GetMapping("/{id}")
    public GetLoanDto getOne(@PathVariable long id){
        return loanService.getOne(id);
    }

    /**
     * Method to create a new loan.
     * @param loan the DTO containing the loan
     * @return CreateLoanResponseDto the DTO containing the created loan
     */
    @PostMapping("/newLoan")
    public ResponseEntity<CreateLoanResponseDto> create(@RequestBody CreateLoanDto loan,HttpServletRequest request){

        String token = processToken.getToken(request);
        //create new loan
        var newLoan = loanService.create(loan, token);
        return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
    }

    /**
     * Method to update the status of a loan.
     * @param updateStatusDto the DTO containing the updated status
     * @param loanId the ID of the loan
     * @return UpdateStatusResponseDto the DTO containing the updated loan
     */
    @PostMapping("/{loanId}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UpdateStatusResponseDto> updateStatus(@RequestBody UpdateStatusDto updateStatusDto, @PathVariable String loanId){

        var loanIdLong = Long.parseLong(loanId.substring(1, loanId.length() - 1));

        var updatedLoan = loanService.updateStatus(updateStatusDto, loanIdLong);
        return new ResponseEntity<>(updatedLoan, HttpStatus.OK);
    }

    /**
     * Method to return a loan.
     * @param returnLoanDto the DTO containing the loan to be returned
     * @return ReturnLoanResponseDto the DTO containing the returned loan
     */
    @PostMapping("/return")
    public ResponseEntity<ReturnLoanResponseDto> returnLoan(@RequestBody ReturnLoanDto returnLoanDto, HttpServletRequest request){

        String token = processToken.getToken(request);

        var returnedLoan = loanService.returnLoan(returnLoanDto, token);
        System.out.println("Processed");
        return new ResponseEntity<>(returnedLoan, HttpStatus.OK);
    }

    /**
     * Method to delete a loan.
     * @param id the ID of the loan
     * @return ResponseEntity<Void> the response entity
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        loanService.delete(id);
        return ResponseEntity.noContent().build();
    }
}