package com.illia.project.ntilliaproject.controller.dto.review;

import java.util.Date;

public class GetReviewDto {

    private int reviewID;

    private int bookId;

    private double rating;

    private String comment;

    private Date reviewDate;

    public GetReviewDto(int reviewID, int bookId, double rating, String comment, Date reviewDate) {
        this.bookId = bookId;
        this.reviewID = reviewID;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    public GetReviewDto() {
    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public int getbookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
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


}
