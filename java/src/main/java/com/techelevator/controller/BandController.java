package com.techelevator.controller;

import com.techelevator.Services.BandService;
import com.techelevator.Services.ImageService;
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
import java.util.List;

@CrossOrigin
@RestController
@PreAuthorize("isAuthenticated()")
public class BandController {

    @Autowired
    private BandDao bandDao;
    @Autowired
    private ImageDao imageDao;
    @Autowired
    private ImageService imageService;
    @Autowired
    private BandService bandService;

    @RequestMapping(path = "/bands/{bandName}", method = RequestMethod.GET)
    public Band getBand(@PathVariable String bandName) {
        return bandService.getBandByName(bandName);
    }

    @RequestMapping(path = "/bands/create", method = RequestMethod.POST)
    public Band createBand(@RequestBody Band bandToAdd) {
        return bandService.createBand(bandToAdd);
    }

    @GetMapping(path = "/bands/search/{searchTerm}")
    public List<Band> getAllBands(@PathVariable String searchTerm) {
        return bandService.getBandsBySimilarName(searchTerm);
    }

    @PostMapping("/photo")
    public Image uploadPhoto(@RequestParam("file")MultipartFile file) {
        try {
            return imageService.uploadImage(file.getOriginalFilename(), file.getBytes());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image data not found.");
        }
    }

    @GetMapping("/photo/{imageId}")
    public Image getPhoto(@PathVariable int imageId) {
        return imageService.getBandImageById(imageId);
    }

//    @PutMapping("/photo/{bandId}")
//    public Image addBandCoverPhoto(@PathVariable int bandId, @RequestBody Image bandImage){
//        return imageService.addCoverImageToBand(bandImage.getImageId(), bandId);
//    }

    @GetMapping("/coverphoto/{bandId}")
    public Image getBandCoverImage(@PathVariable int bandId) {
        return imageService.getCoverImageByBandId(bandId);
    }

    @PostMapping("/coverphoto/{bandId}")
    public Image uploadFile(@RequestParam("file") MultipartFile file, @PathVariable int bandId) {
        try {
            Image imageToLink = imageService.uploadImage(file.getOriginalFilename(), file.getBytes());
            imageDao.addCoverImageToBand(imageToLink.getImageId(), bandId);
            return imageToLink;
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image data not found.");
        }
    }

    @PostMapping("/bands/{bandId}/follow")
    public boolean followBand(@RequestParam int userId, @PathVariable int bandId) {
        return bandService.followBand(userId, bandId);
    }

    @GetMapping("/bands/following")
    public List<Band> getAllUserFollowedBands(@RequestParam int userId) {
        return bandService.getAllUserFollowedBands(userId);
    }

    @GetMapping("/bands/{bandId}/users/{userId}/isfollowing")
    public boolean isFollowing(@PathVariable int userId, @PathVariable int bandId) {
        return bandService.isFollowing(userId, bandId);
    }

    @DeleteMapping("/bands/{bandId}/unfollow")
    public boolean unfollowBand(@RequestParam int userId, @PathVariable int bandId) {
        return bandService.unfollowBand(userId, bandId);
    }
}
