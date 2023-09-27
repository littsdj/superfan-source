package com.techelevator.dao;

import com.techelevator.dao.mapper.ImageMapper;
import com.techelevator.model.Image;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcImageDao implements ImageDao{

    private final JdbcTemplate jdbcTemplate;
    private final ImageMapper imageMapper = new ImageMapper();
    public JdbcImageDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Image uploadImage(String fileName, byte[] data) {
        String sql = "INSERT INTO images (filename, image_data) VALUES " +
                "(?, ?) RETURNING image_id;";
        int bandId = jdbcTemplate.queryForObject(sql, Integer.class, fileName, data);
        return getBandImageById(bandId);
    }

    @Override
    public Image getBandImageById(int imageId) {
        String sql = "SELECT * FROM images WHERE image_id = ?;";
        try {
            return jdbcTemplate.queryForObject(sql, imageMapper, imageId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
