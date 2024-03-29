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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCoverImageURL() {
        return coverImageURL;
    }

    public void setCoverImageURL(String coverImageURL) {
        this.coverImageURL = coverImageURL;
    }
}
