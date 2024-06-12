package com.illia.project.ntilliaproject.controller;

import com.illia.project.ntilliaproject.controller.dto.review.CreateReviewDto;
import com.illia.project.ntilliaproject.controller.dto.review.CreateReviewResponseDto;
import com.illia.project.ntilliaproject.controller.dto.review.GetReviewDto;
import com.illia.project.ntilliaproject.infrastructure.suplementary.ProcessToken;
import com.illia.project.ntilliaproject.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is the controller class for reviews.
 * It handles HTTP requests related to reviews.
 */
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    private final ProcessToken processToken;

    /**
     * Constructor for the ReviewController class.
     * @param reviewService the service that this controller will use to process reviews
     * @param processToken the utility that this controller will use to process tokens
     */
    @Autowired
    public ReviewController(ReviewService reviewService, ProcessToken processToken) {
        this.reviewService = reviewService;
        this.processToken = processToken;
    }

    /**
     * Method to get all reviews.
     * @return List<GetReviewDto> the list of DTOs containing the reviews
     */
    @GetMapping("/all")
    public List<GetReviewDto> getAllReviews(){
        return reviewService.getAll();
    }

    /**
     * Method to get a single review by its ID.
     * @param id the ID of the review
     * @return GetReviewDto the DTO containing the review
     */
    @GetMapping("/{id}")
    public List<GetReviewDto> getOne(@PathVariable String id){
        var bookIdInt = Integer.parseInt(id);
        return reviewService.getOne(bookIdInt);
    }

    /**
     * Method to create a new review.
     * @param review the DTO containing the review
     * @return CreateReviewResponseDto the DTO containing the created review
     */
    @PostMapping("{bookId}/leftReview")
    public ResponseEntity<CreateReviewResponseDto> create(@RequestBody CreateReviewDto review, @PathVariable String bookId, HttpServletRequest request){

        var bookIdInt = Integer.parseInt(bookId);

        //get token from header
        String token = processToken.getToken(request);
        System.out.println(request);
        System.out.println(token);
        System.out.println(bookIdInt);
        var newReview = reviewService.create(review, token, bookIdInt);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    /**
     * Method to delete a review.
     * @param id the ID of the review
     * @return ResponseEntity<Void> the response entity
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        var idLong = Long.parseLong(id.substring(1, id.length() - 1));
        reviewService.delete(idLong);
        return ResponseEntity.noContent().build();
    }
}