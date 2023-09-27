package com.techelevator.Services;

import com.techelevator.dao.BandDao;
import com.techelevator.model.Band;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BandService {
    private final BandDao bandDao;
    public BandService(BandDao bandDao){
        this.bandDao = bandDao;
    }
    public Band getBandByName(String bandName) {
        return bandDao.getBandByName(bandName);
    }

    public List<Band> getBandsByGenre(int genreId) {
        return bandDao.getBandsByGenre(genreId);
    }

    public Band getBandById(int bandId) {
        return bandDao.getBandById(bandId);
    }

    public List<Band> findAllBands() {
        return bandDao.findAllBands();
    }
    public Band updateBand(Band bandToUpdate) {
        int rowsUpdated = bandDao.updateBand(bandToUpdate);
        if(rowsUpdated == 1) {
            return bandDao.getBandById(bandToUpdate.getBandId());
        } else {
            return null;
        }
    }

    public Band createBand(Band band) {
        return bandDao.createBand(band);
    }

    public int getBandIdByBandName(String bandName) {
        return bandDao.getBandIdByBandName(bandName);
    }

    public String getBandNameById(int bandId) {
        return bandDao.getBandNameById(bandId);
    }



}
