package com.techelevator.Services;

import com.techelevator.dao.ImageDao;
import com.techelevator.model.Image;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private final ImageDao imageDao;

    public ImageService(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    public Image uploadImage(String fileName, byte[] data){
        int imageId = imageDao.uploadImage(fileName, data);
        return imageDao.getBandImageById(imageId);
    }

}
