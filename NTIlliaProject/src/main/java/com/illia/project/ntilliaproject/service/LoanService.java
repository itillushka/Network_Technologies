package com.illia.project.ntilliaproject.service;

import com.illia.project.ntilliaproject.controller.dto.loan.CreateLoanDto;
import com.illia.project.ntilliaproject.controller.dto.loan.CreateLoanResponseDto;
import com.illia.project.ntilliaproject.controller.dto.loan.GetLoanDto;
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

        Integer userID = jwtService.extractUserID(token);
        System.out.println(userID);
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

    public void delete(long id){
        if(!loanRepository.existsById(id)){
            throw new RuntimeException("Loan not found");
        }
        loanRepository.deleteById(id);
    }
}
