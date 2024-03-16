package com.illia.project.ntilliaproject.controller.dto.review;

import java.util.Date;

public class GetReviewDto {

    private int reviewID;

    private int rating;

    private String comment;

    private Date reviewDate;

    public GetReviewDto(int reviewID, int rating, String comment, Date reviewDate) {
        this.reviewID = reviewID;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
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

    public GetReviewDto() {
    }
}
