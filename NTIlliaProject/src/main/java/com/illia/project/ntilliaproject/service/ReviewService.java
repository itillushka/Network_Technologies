package com.illia.project.ntilliaproject.service;

import com.illia.project.ntilliaproject.controller.dto.review.CreateReviewDto;
import com.illia.project.ntilliaproject.controller.dto.review.CreateReviewResponseDto;
import com.illia.project.ntilliaproject.controller.dto.review.GetReviewDto;
import com.illia.project.ntilliaproject.infrastructure.entity.ReviewEntity;
import com.illia.project.ntilliaproject.infrastructure.entity.UserEntity;
import com.illia.project.ntilliaproject.infrastructure.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    // dependency injection
    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
    public GetReviewDto getOne(long id){
        var reviewEntity = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
        return new GetReviewDto(
                reviewEntity.getReviewID(),
                reviewEntity.getRating(),
                reviewEntity.getComment(),
                reviewEntity.getReviewDate());

    }
    public List<GetReviewDto> getAll(){
        var users = reviewRepository.findAll();
        return users.stream().map((review)-> new GetReviewDto(
                review.getReviewID(),
                review.getRating(),
                review.getComment(),
                review.getReviewDate())).collect(Collectors.toList());
    }

    public CreateReviewResponseDto create(CreateReviewDto review){
        var reviewEntity = new ReviewEntity();
        reviewEntity.setRating(review.getRating());
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
