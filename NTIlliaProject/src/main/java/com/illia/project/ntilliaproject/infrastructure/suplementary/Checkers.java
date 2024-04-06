package com.illia.project.ntilliaproject.infrastructure.suplementary;

import com.illia.project.ntilliaproject.infrastructure.entity.BookEntity;
import com.illia.project.ntilliaproject.infrastructure.entity.LoanEntity;
import com.illia.project.ntilliaproject.infrastructure.entity.UserEntity;
import com.illia.project.ntilliaproject.infrastructure.repository.*;
import com.illia.project.ntilliaproject.service.error.AlreadyExistsException;
import com.illia.project.ntilliaproject.service.error.NotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


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
        return bookRepository.findBybookID(bookId)
                .orElseThrow(() -> NotFoundException.create("Book"));
    }

    public UserEntity checkIfUserExists(int userID) {
        return userRepository.findByuserID(userID)
                .orElseThrow(() -> NotFoundException.create("User"));
    }
    @Transactional
    public LoanEntity checkIfLoanExists(long loanID) {
        return loanRepository.findByloanID(loanID)
                .orElseThrow(() -> new NotFoundException("Loan"));
    }

    public boolean checkIfLoanExistsByUserAndBook(UserEntity userEntity, BookEntity bookEntity){
        return loanRepository.existsByUserAndBook(userEntity, bookEntity);
    }


    public LoanEntity checkIfLoanExistsByUserAndBook(Integer userID, Integer bookID){

        return loanRepository.findByUser_userIDAndBook_bookID(userID, bookID)
                .orElseThrow(() -> NotFoundException.create("Loan"));
    }


    public boolean checkIfReviewExistsByUserAndBook(UserEntity userEntity, BookEntity bookEntity){
        return reviewRepository.existsByUserAndBook(userEntity, bookEntity);
    }
}
