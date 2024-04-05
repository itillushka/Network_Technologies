package com.illia.project.ntilliaproject.controller.dto.review;

import java.util.Date;

public class CreateReviewDto {

    private double rating;

    private String comment;

    private Date reviewDate;

    public CreateReviewDto( double rating, String comment, Date reviewDate) {
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }


    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public CreateReviewDto() {
    }
}
