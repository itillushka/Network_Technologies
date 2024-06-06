package com.illia.project.ntilliaproject.service;

import com.illia.project.ntilliaproject.commonTypes.LoanStatus;
import com.illia.project.ntilliaproject.controller.dto.loan.*;
import com.illia.project.ntilliaproject.infrastructure.entity.LoanEntity;
import com.illia.project.ntilliaproject.infrastructure.repository.BookRepository;
import com.illia.project.ntilliaproject.infrastructure.repository.LoanRepository;
import com.illia.project.ntilliaproject.infrastructure.suplementary.Checkers;
import com.illia.project.ntilliaproject.service.error.LoanNotBorrowedException;
import com.illia.project.ntilliaproject.service.error.NoBooksAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;

    private final JwtService jwtService;
    private final Checkers checkers;


    // dependency injection
    @Autowired
    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, JwtService jwtService, Checkers checkers) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.jwtService = jwtService;
        this.checkers = checkers;
    }
    public GetLoanDto getOne(long id){
        var loanEntity= loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan not found"));
        return new GetLoanDto(
                loanEntity.getLoanID(),
                loanEntity.getBook().getBookID(),
                loanEntity.getLoanDate(),
                loanEntity.getDueDate(),
                loanEntity.getReturnDate(),
                loanEntity.getStatus());

    }
    public List<GetLoanDto> getAll(String token){
        Integer userID = jwtService.extractUserID(token);
        var loans= loanRepository.findByUser_userID(userID);
        return loans.stream().map((loan)-> new GetLoanDto(
                loan.getLoanID(),
                loan.getBook().getBookID(),
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getReturnDate(),
                loan.getStatus())).collect(Collectors.toList());
    }

    public List<GetLoanDto> getAllAdmin(){
        var loans= loanRepository.findAll();
        return loans.stream().map((loan)-> new GetLoanDto(
                loan.getLoanID(),
                loan.getBook().getBookID(),
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getReturnDate(),
                loan.getStatus())).collect(Collectors.toList());
    }

    public CreateLoanResponseDto create(CreateLoanDto loan, String token){
        var bookEntity = checkers.checkIfBookExists(loan.getBookID());
        // extract user id from token
        Integer userID = jwtService.extractUserID(token);
        System.out.println(userID);
        // find user by userid
        var userEntity = checkers.checkIfUserExists(userID);

        if (bookEntity.getAvailableCopies() <= 0) {
            throw NoBooksAvailableException.create();
        }

        bookEntity.setAvailableCopies(bookEntity.getAvailableCopies() - 1);
        bookRepository.save(bookEntity);

        var loanEntity = new LoanEntity();
        loanEntity.setBook(bookEntity);
        loanEntity.setUser(userEntity);
        loanEntity.setLoanDate(loan.getLoanDate());
        loanEntity.setDueDate(loan.getDueDate());
        var newLoan= loanRepository.save(loanEntity);
        return new CreateLoanResponseDto(newLoan.getLoanID(),
                userID,
                newLoan.getLoanDate(),
                newLoan.getDueDate());
    }

    @Transactional
    public UpdateStatusResponseDto updateStatus(UpdateStatusDto updateStatusDto, long loanId){
        var loanEntity = checkers.checkIfLoanExists(loanId);
        loanEntity.setStatus(updateStatusDto.getStatus());
        loanRepository.save(loanEntity);
        return new UpdateStatusResponseDto(loanEntity.getLoanID(), loanEntity.getStatus());
    }

    @Transactional
    public ReturnLoanResponseDto returnLoan(ReturnLoanDto returnLoanDto, String token){
        Integer userID = jwtService.extractUserID(token);
        System.out.println("userId" + userID);

        var userEntity = checkers.checkIfUserExists(userID);

        var bookEntity = checkers.checkIfBookExists(returnLoanDto.getBookId());

        var loanEntity = checkers.checkIfLoanExistsByUserAndBook(userEntity.getUserID(), bookEntity.getBookID());


        if (loanEntity.getStatus() != LoanStatus.APPROVED) {
            throw LoanNotBorrowedException.create();
        }

        // Check if return date is after due date
        if (returnLoanDto.getReturnDate().after(loanEntity.getDueDate())) {
            loanEntity.setStatus(LoanStatus.OVERDUE);
        } else {
            loanEntity.setStatus(LoanStatus.PENDING_RETURN);
        }

        bookEntity.setAvailableCopies(bookEntity.getAvailableCopies() + 1);
        bookRepository.save(bookEntity);

        loanEntity.setReturnDate(returnLoanDto.getReturnDate());
        loanRepository.save(loanEntity);

        return new ReturnLoanResponseDto(loanEntity.getLoanID(), loanEntity.getReturnDate(), loanEntity.getStatus());
    }

    public void delete(long id){
        if(!loanRepository.existsById(id)){
            throw new RuntimeException("Loan not found");
        }
        loanRepository.deleteById(id);
    }
}
