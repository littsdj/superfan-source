package com.techelevator.dao;

import com.techelevator.model.BandImage;

public interface ImageDao {
    BandImage uploadImage(String fileName, byte[] data);
    BandImage getBandImageById(int imageId);
}
