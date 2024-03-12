package com.illia.project.ntilliaproject.service;

import com.illia.project.ntilliaproject.infrastructure.entity.ReviewEntity;
import com.illia.project.ntilliaproject.infrastructure.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    // dependency injection
    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
    public List<ReviewEntity> getAll(){
        return reviewRepository.findAll();
    }
}
