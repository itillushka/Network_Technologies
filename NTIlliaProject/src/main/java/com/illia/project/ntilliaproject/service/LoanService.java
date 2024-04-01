package com.illia.project.ntilliaproject.service;

import com.illia.project.ntilliaproject.commonTypes.LoanStatus;
import com.illia.project.ntilliaproject.controller.dto.loan.*;
import com.illia.project.ntilliaproject.infrastructure.entity.LoanEntity;
import com.illia.project.ntilliaproject.infrastructure.repository.BookRepository;
import com.illia.project.ntilliaproject.infrastructure.repository.LoanRepository;
import com.illia.project.ntilliaproject.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;

    private  final UserRepository userRepository;

    private final JwtService jwtService;


    // dependency injection
    @Autowired
    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, UserRepository userRepository, JwtService jwtService) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
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

    public CreateLoanResponseDto create(CreateLoanDto loan, String token){
        var bookEntity = bookRepository.findBybookID(loan.getBookID())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        System.out.println("Book found");

        // extract user id from token
        Integer userID = jwtService.extractUserID(token);
        System.out.println(userID);
        // find user by userid
        var userEntity = userRepository.findByuserID(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (bookEntity.getAvailableCopies() <= 0) {
            throw new RuntimeException("No copies of the book are available");
        }

        bookEntity.setAvailableCopies(bookEntity.getAvailableCopies() - 1);
        bookRepository.save(bookEntity);

        var loanEntity = new LoanEntity();
        loanEntity.setBook(bookEntity);
        loanEntity.setUser(userEntity);
        loanEntity.setLoanDate(loan.getLoanDate());
        loanEntity.setDueDate(loan.getDueDate());
        loanEntity.setReturnDate(loan.getReturnDate());
        var newLoan= loanRepository.save(loanEntity);
        return new CreateLoanResponseDto(newLoan.getLoanID(),
                userID,
                newLoan.getLoanDate(),
                newLoan.getDueDate(),
                newLoan.getReturnDate());
    }

    public UpdateStatusResponseDto updateStatus(UpdateStatusDto updateStatusDto, long loanId){
        var loanEntity = loanRepository.findByloanID(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        loanEntity.setStatus(updateStatusDto.getStatus());
        loanRepository.save(loanEntity);
        return new UpdateStatusResponseDto(loanEntity.getLoanID(), loanEntity.getStatus());
    }

    public ReturnLoanResponseDto returnLoan(ReturnLoanDto returnLoanDto, long loanId, String token){
        var loanEntity = loanRepository.findByloanID(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        System.out.println("Loan found");
        if (loanEntity.getStatus() != LoanStatus.APPROVED) {
            throw new RuntimeException("Loan is not borrowed");
        }
        System.out.println("Loan is borrowed");
        Integer userID = jwtService.extractUserID(token);
        if (loanEntity.getUser().getUserID() != userID) {
            throw new RuntimeException("Loan does not belong to the user");
        }

        var bookEntity = bookRepository.findBybookID(loanEntity.getBook().getBookID())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        bookEntity.setAvailableCopies(bookEntity.getAvailableCopies() + 1);
        System.out.println("All criteria passed");
        loanEntity.setReturnDate(returnLoanDto.getReturnDate());
        loanEntity.setStatus(LoanStatus.PENDING_RETURN);
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
