package com.techelevator.dao;

import com.techelevator.model.Band;

import java.util.List;

public interface BandDao {

    List<Band> findAllBands();
    Band getBandById(int bandId);
    Band getBandByName(String bandName);
    List<Band> getBandsByGenre(int genreId);
    int updateBand(Band bandToUpdate);
    int getBandIdByBandName(String bandName);
    String getBandNameById(int bandId);
    Band createBand(Band band);

}
