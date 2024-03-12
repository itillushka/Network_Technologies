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

}