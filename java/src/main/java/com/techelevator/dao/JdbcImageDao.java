package com.techelevator.dao;

import com.techelevator.model.BandImage;
import org.springframework.stereotype.Component;

@Component
public class JdbcImageDao implements ImageDao{

    @Override
    public BandImage uploadImage(String fileName, byte[] data) {
        BandImage bandImage = new BandImage();
        bandImage.setFileName(fileName);
//        bandImage.setImageData(data);

        return null;
    }

    @Override
    public BandImage getBandImageById(int imageId) {

        return null;
    }
}
