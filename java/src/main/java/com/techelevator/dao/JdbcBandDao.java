package com.techelevator.dao;

import com.techelevator.model.Band;
import com.techelevator.model.Subgenre;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBandDao implements BandDao{

    /*
    * TODO: Exception handling.
    * */
    private final JdbcTemplate jdbcTemplate;

    public JdbcBandDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Band> findAllBands() {
        List<Band> bands = new ArrayList<>();
        String sql = "SELECT * FROM bands";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Band band = mapRowToBand(results);
            bands.add(band);
        }

        return bands;
    }

    @Override
    public Band getBandById(int bandId) {
        Band bandToReturn = new Band();
        String sql = "SELECT * FROM bands WHERE band_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, bandId);

        if(results.next()) {
            bandToReturn = mapRowToBand(results);
        }

        return bandToReturn;
    }

    @Override
    public Band getBandByName(String bandName) {
        Band bandToReturn = new Band();
        String sql = "SELECT * FROM bands WHERE band_name = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, bandName);
        if(results.next()) {
            bandToReturn = mapRowToBand(results);
        }

        return bandToReturn;
    }

    @Override
    public List<Band> getBandsByGenre(int genreId) {
        List<Band> bands = new ArrayList<>();
        String sql = "SELECT * FROM bands JOIN genres ON (bands.genre_id = genres.genre_id) WHERE bands.genre_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, genreId);
        while(results.next()) {
            Band band = mapRowToBand(results);
            bands.add(band);
        }

        return bands;
    }
    /*
     * TODO: Determine image format and update
     *  this method to properly populate the band object.
     * */
    public Band createBand(Band band) {
        Band bandToReturn = new Band();
        String sql = "INSERT INTO bands (band_name, description, genre_id, " +
                "cover_image_url) VALUES (?, ?, ?, ?) RETURNING band_id;";

        int bandId = 0;
        try {
            bandId = jdbcTemplate.queryForObject(sql, Integer.class, band.getBandName(),
                    band.getDescription(), band.getGenreId(), band.getCoverImageUrl());
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not create band.");
        }

        bandToReturn.setBandId(band.getBandId());
        return bandToReturn;
    }

    /*
    * TODO: Determine image format and update
    *  this method to properly populate the band object.
    * */
    @Override
    public Band updateBand(Band bandToUpdate) {
        String sql = "UPDATE bands SET (band_name = ?, description = ?, genre_id = ?,  WHERE user_id = ?;";


        return null;
    }

    @Override
    public int getBandIdByBandName(String bandName) {
        String sql = "SELECT * FROM bands WHERE band_name = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, bandName);
        int bandId = 0;

        if(results.next()) {
            bandId = results.getInt("band_id");
        }
        return bandId;
    }

    @Override
    public String getBandNameById(int bandId) {
        String bandName = null;
        String sql = "SELECT * FROM bands WHERE band_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, bandId);
        if(results.next()) {
            bandName = results.getString("band_name");
        }

        return bandName;
    }


    /*
     * TODO: Determine image format and update
     *  this method to properly populate the band object.
     * */
    private Band mapRowToBand(SqlRowSet rs) {
        Band band = new Band();
        band.setBandId(rs.getInt("band_id"));
        band.setBandName(rs.getString("band_name"));
        band.setCoverImageUrl(rs.getString("cover_image_url"));
        band.setDescription(rs.getString("description"));
        band.setGenreId(rs.getInt("genre_id"));
        band.setSubgenres(getAllSubgenresByBandId(band.getBandId()));
        return band;
    }

    private List<Subgenre> getAllSubgenresByBandId(int bandId) {
        List<Subgenre> subgenres = new ArrayList<>();
        String sql = "SELECT * FROM subgenres JOIN band_subgenres " +
                "ON (subgenres.subgenre_id = band_subgenres.subgenre_id) " +
                "WHERE band_subgenres.subgenre_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, bandId);
        while(results.next()) {
            Subgenre subgenre = new Subgenre();
            subgenre.setSubgenreId(results.getInt("subgenre_id"));
            subgenre.setSubgenreName(results.getString("subgenre_name"));
            subgenres.add(subgenre);
        }
        return subgenres;
    }
}
