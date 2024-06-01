package com.illia.project.ntilliaproject.controller.dto.bookDetails;

public class CreateBookDetailsDto {

    private Integer bookId;

    private String genre;

    private String summary;

    private String coverImageURL;

    public CreateBookDetailsDto(Integer bookId, String genre, String summary, String coverImageURL) {
        this.bookId = bookId;
        this.genre = genre;
        this.summary = summary;
        this.coverImageURL = coverImageURL;
    }

    public CreateBookDetailsDto() {
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
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
