package com.techelevator.dao;

import com.techelevator.model.Image;

public interface ImageDao {
    int uploadImage(String fileName, byte[] data);
    Image getBandImageById(int imageId);
    Image getCoverImageByBandId(int bandId);
    Integer addCoverImageToBand(int imageId, int bandId);
}
