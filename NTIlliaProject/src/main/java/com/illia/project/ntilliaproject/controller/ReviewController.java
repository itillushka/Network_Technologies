package com.illia.project.ntilliaproject.controller;

import com.illia.project.ntilliaproject.controller.dto.review.CreateReviewDto;
import com.illia.project.ntilliaproject.controller.dto.review.CreateReviewResponseDto;
import com.illia.project.ntilliaproject.controller.dto.review.GetReviewDto;
import com.illia.project.ntilliaproject.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/all")
    public List<GetReviewDto> getAllReviews(){
        return reviewService.getAll();
    }

    @GetMapping("/{id}")
    public GetReviewDto getOne(@PathVariable long id){
        return reviewService.getOne(id);
    }

    @PostMapping("{bookId}/leftReview")
    public ResponseEntity<CreateReviewResponseDto> create(@RequestBody CreateReviewDto review, @PathVariable String bookId, HttpServletRequest request){

        var bookIdInt = Integer.parseInt(bookId.substring(1, bookId.length() - 1));

        //get token from header
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        //remove Bearer from token
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        var newReview = reviewService.create(review, token, bookIdInt);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        var idLong = Long.parseLong(id.substring(1, id.length() - 1));
        reviewService.delete(idLong);
        return ResponseEntity.noContent().build();
    }
}