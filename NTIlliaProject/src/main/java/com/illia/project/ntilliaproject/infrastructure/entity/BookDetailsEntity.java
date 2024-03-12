package com.illia.project.ntilliaproject.infrastructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bookDetails", schema = "library")
public class BookDetailsEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bookID", referencedColumnName = "bookID")
    private BookEntity book;

    @Basic
    @Column(name = "genre")
    private String genre;

    @Basic
    @Column(name = "summary")
    private String summary;

    @Basic
    @Column(name = "coverImageURL")
    private String coverImageURL;

    // getters and setters
}
