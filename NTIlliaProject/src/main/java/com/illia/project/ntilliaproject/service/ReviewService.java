package com.illia.project.ntilliaproject.service;

import com.illia.project.ntilliaproject.controller.dto.review.CreateReviewDto;
import com.illia.project.ntilliaproject.controller.dto.review.CreateReviewResponseDto;
import com.illia.project.ntilliaproject.controller.dto.review.GetReviewDto;
import com.illia.project.ntilliaproject.infrastructure.entity.ReviewEntity;
import com.illia.project.ntilliaproject.infrastructure.repository.ReviewRepository;
import com.illia.project.ntilliaproject.infrastructure.suplementary.Checkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final JwtService jwtService;

    private final Checkers checkers;

    // dependency injection
    @Autowired
    public ReviewService(ReviewRepository reviewRepository,
                         JwtService jwtService, Checkers checkers) {
        this.reviewRepository = reviewRepository;
        this.jwtService = jwtService;
        this.checkers = checkers;
    }
    public List<GetReviewDto> getOne(int id){
        var reviews = reviewRepository.findBybook_bookID(id);
        return reviews.stream().map((review)-> new GetReviewDto(
                review.getReviewID(),
                review.getBook().getBookID(),
                review.getRating(),
                review.getComment(),
                review.getReviewDate())).collect(Collectors.toList());

    }
    public List<GetReviewDto> getAll(){
        var users = reviewRepository.findAll();
        return users.stream().map((review)-> new GetReviewDto(
                review.getReviewID(),
                review.getBook().getBookID(),
                review.getRating(),
                review.getComment(),
                review.getReviewDate())).collect(Collectors.toList());
    }

    public CreateReviewResponseDto create(CreateReviewDto review, String token, Integer bookId){

        var bookEntity = checkers.checkIfBookExists(bookId);
        // extract user id from token
        Integer userID = jwtService.extractUserID(token);
        // find user by userid
        var userEntity = checkers.checkIfUserExists(userID);
        // check if user has borrowed the book
        boolean hasBorrowed = checkers.checkIfLoanExistsByUserAndBook(userEntity, bookEntity);
        if (!hasBorrowed) {
            throw new RuntimeException("User has not borrowed this book");
        }

        boolean hasReviewed = checkers.checkIfReviewExistsByUserAndBook(userEntity, bookEntity);
        if (hasReviewed) {
            throw new RuntimeException("User has already reviewed this book");
        }


        var reviewEntity = new ReviewEntity();
        reviewEntity.setRating(review.getRating());
        reviewEntity.setBook(bookEntity);
        reviewEntity.setUser(userEntity);
        reviewEntity.setComment(review.getComment());
        reviewEntity.setReviewDate(review.getReviewDate());
        var newReview= reviewRepository.save(reviewEntity);
        return new CreateReviewResponseDto(newReview.getReviewID(),
                newReview.getRating(),
                newReview.getComment(),
                newReview.getReviewDate());
    }

    public void delete(long id){
        if(!reviewRepository.existsById(id)){
            throw new RuntimeException("Review not found");
        }
        reviewRepository.deleteById(id);
    }
}
