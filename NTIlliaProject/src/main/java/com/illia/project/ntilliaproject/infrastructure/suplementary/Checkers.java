package com.illia.project.ntilliaproject.infrastructure.suplementary;

import com.illia.project.ntilliaproject.infrastructure.entity.BookEntity;
import com.illia.project.ntilliaproject.infrastructure.entity.LoanEntity;
import com.illia.project.ntilliaproject.infrastructure.entity.UserEntity;
import com.illia.project.ntilliaproject.infrastructure.repository.*;
import com.illia.project.ntilliaproject.service.error.AlreadyExistsException;
import com.illia.project.ntilliaproject.service.error.NotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@Component
public class Checkers {

    private final BookDetailsRepository bookDetailsRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final LoanRepository loanRepository;
    private final ReviewRepository reviewRepository;

    public Checkers(BookDetailsRepository bookDetailsRepository, BookRepository bookRepository, UserRepository userRepository, LoanRepository loanRepository, ReviewRepository reviewRepository) {
        this.bookDetailsRepository = bookDetailsRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
        this.reviewRepository = reviewRepository;
    }

    public void checkIfBookDetailsExists(int bookId) {
        var bookDetailsEntity = bookDetailsRepository.findBybook_bookID(bookId);
        if (bookDetailsEntity.isPresent()) {
            throw AlreadyExistsException.create("Book details");
        }
    }

    public BookEntity checkIfBookExists(int bookId) {
        var bookEntity = bookRepository.findBybookID(bookId)
                .orElseThrow(() -> NotFoundException.create("Book"));
        return bookEntity;
    }

    public UserEntity checkIfUserExists(int userID) {
        var userEntity = userRepository.findByuserID(userID)
                .orElseThrow(() -> NotFoundException.create("User"));
        return userEntity;
    }
    @Transactional
    public LoanEntity checkIfLoanExists(long loanID) {
        var loanEntity = loanRepository.findByloanID(loanID)
                .orElseThrow(() -> new NotFoundException("Loan"));
        return loanEntity;
    }

    public boolean checkIfLoanExistsByUserAndBook(UserEntity userEntity, BookEntity bookEntity){
        boolean hasBorrowed = loanRepository.existsByUserAndBook(userEntity, bookEntity);
        return hasBorrowed;
    }


    public LoanEntity checkIfLoanExistsByUserAndBook(Integer userID, Integer bookID){
        var loanEntity = loanRepository.findByUser_userIDAndBook_bookID(userID, bookID)
                .orElseThrow(() -> NotFoundException.create("Loan"));

        return loanEntity;
    }


    public boolean checkIfReviewExistsByUserAndBook(UserEntity userEntity, BookEntity bookEntity){
        boolean hasReviewed = reviewRepository.existsByUserAndBook(userEntity, bookEntity);
        return hasReviewed;
    }
}
