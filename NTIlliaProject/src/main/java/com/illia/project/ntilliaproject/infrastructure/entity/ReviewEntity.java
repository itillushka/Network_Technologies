package com.illia.project.ntilliaproject.infrastructure.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "reviews", schema = "library")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewID")
    private int reviewID;

    @ManyToOne
    @JoinColumn(name = "bookID", referencedColumnName = "bookID")
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private UserEntity user;

    @Basic
    @Column(name = "rating")
    private int rating;

    @Basic
    @Column(name = "comment")
    private String comment;

    @Basic
    @Column(name = "reviewDate")
    @Temporal(TemporalType.DATE)
    private Date reviewDate;

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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
}