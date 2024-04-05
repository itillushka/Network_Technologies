package com.illia.project.ntilliaproject.controller.dto.bookDetails;


public class GetBookDetailsDto {

    private Long id;

    private String genre;

    private String summary;

    private String coverImageURL;

    public GetBookDetailsDto(Long id, String genre, String summary, String coverImageURL) {
        this.id = id;
        this.genre = genre;
        this.summary = summary;
        this.coverImageURL = coverImageURL;
    }

    public GetBookDetailsDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
