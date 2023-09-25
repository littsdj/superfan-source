package com.techelevator.dao;

import com.techelevator.model.Band;

import java.util.List;

public interface BandDao {

    List<Band> findAllBand();
    Band getBandById(int bandId);
    Band getBandByName(String bandName);
    List<Band> getBandsByGenre(String genreName);
    Band updateBand(Band bandToUpdate);


}
