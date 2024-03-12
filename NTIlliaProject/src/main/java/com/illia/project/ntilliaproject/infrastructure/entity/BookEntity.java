package com.illia.project.ntilliaproject.infrastructure.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books", schema = "library")
public class BookEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "bookID")
    private int bookID;

    @Basic
    @Column(name = "isbn")
    private String ISBN;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "author")
    private String author;

    @Basic
    @Column(name = "publisher")
    private String publisher;

    @Basic
    @Column(name = "yearPublished")
    private int yearPublished;

    @Basic
    @Column(name = "availableCopies")
    private int availableCopies;

    @OneToMany(mappedBy = "book")
    private List<LoanEntity> loans;

    @OneToMany(mappedBy = "book")
    private List<ReviewEntity> reviews;

}
