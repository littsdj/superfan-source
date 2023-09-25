package com.techelevator.model;

import java.util.List;

public class Band {
    private int bandId;
    private String bandName;
    private String description;
    private String coverImageUrl;
    private String genre;
    private List<String> subgenres;

    public Band() {
    }

    public int getBandId() {
        return bandId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public String getGenres() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<String> getSubgenres() {
        return subgenres;
    }

    public void setSubgenres(List<String> subgenres) {
        this.subgenres = subgenres;
    }
}
