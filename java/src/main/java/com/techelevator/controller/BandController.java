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

    @RequestMapping(path = "/bands/create/{userId}", method = RequestMethod.POST)
    public Band createBand(@RequestBody Band bandToAdd, @PathVariable int userId) {
        return bandService.createBand(bandToAdd, userId);
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

    @RequestMapping(path = "/bands/{bandId}/follow/{userId}", method = RequestMethod.POST)
    public boolean followBand(@PathVariable int userId, @PathVariable int bandId) {
        return bandService.followBand(userId, bandId);
    }

    @GetMapping("/bands/following/{userId}")
    public List<Band> getAllUserFollowedBands(@PathVariable int userId) {
        return bandService.getAllUserFollowedBands(userId);
    }

    @GetMapping("/bands/{bandId}/users/{userId}/isfollowing")
    public boolean isFollowing(@PathVariable int userId, @PathVariable int bandId) {
        return bandService.isFollowing(userId, bandId);
    }

    @DeleteMapping("/bands/{bandId}/unfollow/{userId}")
    public boolean unfollowBand(@PathVariable int userId, @PathVariable int bandId) {
        return bandService.unfollowBand(userId, bandId);
    }

    @PostMapping("/bands/{bandId}/owner/{userId}")
    public boolean setBandOwner(@PathVariable int userId, @PathVariable int bandId) {
        return bandService.setBandOwner(userId, bandId);
    }

    @DeleteMapping("/bands/{bandId}/owner/{userId}")
    public boolean removeBandOwner(@PathVariable int userId, @PathVariable int bandId) {
        return bandService.removeBandOwner(userId, bandId);
    }

    @GetMapping("/bands/{bandId}/owner")
    public int getBandOwnerIdByBandId(@PathVariable int bandId) {
        return bandService.getBandOwnerIdByBandId(bandId);
    }

    @GetMapping("/bands/ownedbands/{userId}")
    public List<Integer> getBandIdsByOwnerId(@PathVariable int userId) {
        return bandService.getBandsIdsByOwnerId(userId);
    }
}
