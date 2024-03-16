package com.illia.project.ntilliaproject.controller.dto.book;


public class GetBookDto {

    private int bookID;

    private String ISBN;

    private String title;

    private String author;

    private String publisher;

    private int yearPublished;

    private boolean isAvailable;

    public GetBookDto(int bookID, String ISBN, String title, String author, String publisher, int yearPublished, boolean isAvailable) {
        this.bookID = bookID;
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
        this.isAvailable = isAvailable;
    }

    public GetBookDto() {
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void seIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
