package com.techelevator.model;

import java.util.List;

public class Band {
    private int bandId;
    private String bandName;
    private String description;
    /*
     * TODO: Change this variable to reflect proper file type for image.
     * */
    private String coverImageUrl;
    private int genreId;
    private List<Subgenre> subgenres;

    /*
    * TODO: Determine proper constructor for class.
    * */
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

    /*
    * TODO: Change this method to reflect proper file type for image.
    * */
    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    /*
     * TODO: Change this method to reflect proper file type for image.
     * */
    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public List<Subgenre> getSubgenres() {
        return subgenres;
    }

    public void setSubgenres(List<Subgenre> subgenres) {
        this.subgenres = subgenres;
    }
}
