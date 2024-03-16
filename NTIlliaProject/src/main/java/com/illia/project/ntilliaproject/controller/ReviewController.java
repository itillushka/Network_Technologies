package com.illia.project.ntilliaproject.controller;

import com.illia.project.ntilliaproject.controller.dto.review.CreateReviewDto;
import com.illia.project.ntilliaproject.controller.dto.review.CreateReviewResponseDto;
import com.illia.project.ntilliaproject.controller.dto.review.GetReviewDto;
import com.illia.project.ntilliaproject.infrastructure.entity.ReviewEntity;
import com.illia.project.ntilliaproject.infrastructure.entity.UserEntity;
import com.illia.project.ntilliaproject.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<GetReviewDto> getAllReviews(){
        return reviewService.getAll();
    }

    @GetMapping("/{id}")
    public GetReviewDto getOne(@PathVariable long id){
        return reviewService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<CreateReviewResponseDto> create(@RequestBody CreateReviewDto review){
        var newReview = reviewService.create(review);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}