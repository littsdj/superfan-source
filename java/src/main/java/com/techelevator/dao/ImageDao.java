package com.techelevator.dao;

import com.techelevator.model.Image;

public interface ImageDao {
    Image uploadImage(String fileName, byte[] data);
    Image getBandImageById(int imageId);
}
