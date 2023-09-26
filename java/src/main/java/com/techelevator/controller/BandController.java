package com.techelevator.controller;

import com.techelevator.dao.BandDao;
import com.techelevator.dao.ImageDao;
import com.techelevator.model.Band;
import com.techelevator.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@PreAuthorize("isAuthenticated()")
public class BandController {

    @Autowired
    private BandDao bandDao;
    @Autowired
    private ImageDao imageDao;

    @RequestMapping(path="/bands/{id}", method = RequestMethod.GET)
    public Band getBand(@PathVariable int bandId) {
        return bandDao.getBandById(bandId);
    }

    @RequestMapping(path="/bands/create", method = RequestMethod.POST)
    public Band createBand(@RequestBody Band bandToAdd){
        return bandDao.createBand(bandToAdd);
    }

//    @RequestMapping(path="/bands/search/{band_name}")

    @PostMapping("/uploadphoto")
    public Image uploadPhoto(@RequestParam("file")MultipartFile file){
        // TODO: Properly integrate method.
        try {
            return imageDao.uploadImage(file.getOriginalFilename(), file.getBytes());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image data not found.");
        }
    }

    @GetMapping("/photo/{imageId}")
    public Image getPhoto(@PathVariable int imageId) {
        return imageDao.getBandImageById(imageId);
    }

}
