package com.techelevator.dao;

import com.techelevator.dao.mapper.BandMapper;
import com.techelevator.model.Band;
import com.techelevator.model.Subgenre;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBandDao implements BandDao {

    /*
    * TODO: Exception handling.
    * */
    private final BandMapper bandMapper = new BandMapper();
    private final JdbcTemplate jdbcTemplate;

    public JdbcBandDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Band> findAllBands() {
        String sql = "SELECT * FROM bands;";

        return jdbcTemplate.query(sql, bandMapper);
    }

    @Override
    public Band getBandById(int bandId) {
        String sql = "SELECT * FROM bands WHERE band_id = ?;";
        try {
            return jdbcTemplate.queryForObject(sql, bandMapper, bandId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Band not found");
        }
    }

    @Override
    public Band getBandByName(String bandName) {
        String sql = "SELECT * FROM bands WHERE band_name = ?;";
        try {
            return jdbcTemplate.queryForObject(sql, bandMapper, bandName);
        } catch(EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Band not found");
        }
    }

    @Override
    public List<Band> getBandsByGenre(int genreId) {
        String sql = "SELECT * FROM bands WHERE genre_id = ?;";

        return jdbcTemplate.query(sql, bandMapper, genreId);
    }

    public Band createBand(Band band) {

        String sql = "INSERT INTO bands (band_name, description, genre_id)" +
                " VALUES (?, ?, ?) RETURNING band_id;";

        int bandId = 0;
        try {
            bandId = jdbcTemplate.queryForObject(sql, Integer.class, band.getBandName(),
                    band.getDescription(), band.getGenreId());
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Band data not present.");
        }

        band.setBandId(bandId);

        return band;
    }

    /*
    * TODO: Determine image format and update
    *  this method to properly populate the band object.
    * */
    @Override
    public int updateBand(Band bandToUpdate) {
        String sql = "UPDATE bands SET (band_name = ?, description = ?, genre_id = ?, " +
                "band_image_id = ?) WHERE band_id = ?;";
        try {
            return jdbcTemplate.update(sql, Integer.class, bandToUpdate.getBandName(),
                    bandToUpdate.getDescription(), bandToUpdate.getGenreId(),
                    bandToUpdate.getBandImage().getImageId(), bandToUpdate.getBandId());
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to update band");
        }
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

    private List<Subgenre> getAllSubgenresByBandId(int bandId) {
        List<Subgenre> subgenres = new ArrayList<>();
        String sql = "SELECT * FROM subgenres JOIN band_subgenres " +
                "ON (subgenres.subgenre_id = band_subgenres.subgenre_id) " +
                "WHERE band_subgenres.subgenre_id = ?;";

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
