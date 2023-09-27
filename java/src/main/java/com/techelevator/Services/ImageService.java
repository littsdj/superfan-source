package com.techelevator.Services;

import com.techelevator.dao.ImageDao;
import com.techelevator.dao.JdbcImageDao;
import com.techelevator.model.Image;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private final ImageDao imageDao;
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public ImageService() {
        this.imageDao = new JdbcImageDao(jdbcTemplate);
    }


}
