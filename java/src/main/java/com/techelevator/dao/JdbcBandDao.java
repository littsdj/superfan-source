package com.techelevator.dao;

import com.techelevator.model.Band;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBandDao implements BandDao{

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
        return null;
    }

    @Override
    public Band getBandByName(String bandName) {
        return null;
    }

    @Override
    public List<Band> getBandsByGenre(String genreName) {
        return null;
    }

    @Override
    public Band updateBand(Band bandToUpdate) {
        return null;
    }

    @Override
    public int getBandIdByBandName(String bandName) {
        return 0;
    }

    @Override
    public String getBandNameById(int bandId) {
        return null;
    }

    private Band mapRowToBand(SqlRowSet rs) {
        Band band = new Band();
        band.setBandId(rs.getInt("band_id"));
        band.setBandName(rs.getString("band_name"));
        band.setCoverImageUrl(rs.getString("cover_image_url"));
        band.setDescription(rs.getString("description"));
        band.setGenre(rs.getString("genre"));
        band.setSubgenres(getAllSubgenresByBandId(band.getBandId()));
        return band;
    }

    private List<String> getAllSubgenresByBandId(int bandId) {
        List<String> subgenres = new ArrayList<>();
        String sql = "SELECT * FROM subgenres JOIN band_subgenres " +
                "ON (subgenres.subgenre_id = band_subgenres.subgenre_id) " +
                "WHERE band_subgenres.subgenre_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, bandId);
        while(results.next()) {
            subgenres.add(results.getString("subgenre_name"));
        }
        return subgenres;
    }
}
