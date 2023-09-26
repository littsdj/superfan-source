package com.techelevator.controller;

import com.techelevator.dao.BandDao;
import com.techelevator.model.Band;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@PreAuthorize("isAuthenticated()")
public class BandController {

    @Autowired
    private BandDao bandDao;

    @RequestMapping(path="/bands/{id}", method = RequestMethod.GET)
    public Band getBand(@PathVariable int bandId) {
        return bandDao.getBandById(bandId);
    }

    @RequestMapping(path="/bands/create", method = RequestMethod.POST)
    public Band createBand(@RequestBody Band bandToAdd) {
        return bandDao.createBand(bandToAdd);
    }

    @RequestMapping(path="/bands/search/{band_name}")

}
