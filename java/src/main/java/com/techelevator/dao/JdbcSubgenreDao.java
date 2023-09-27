package com.techelevator.dao;

import com.techelevator.dao.mapper.BandMapper;
import com.techelevator.dao.mapper.SubgenreMapper;
import com.techelevator.model.Band;
import com.techelevator.model.Subgenre;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class JdbcSubgenreDao implements SubgenreDao {

    private final SubgenreMapper subgenreMapper = new SubgenreMapper();
    private final BandMapper bandMapper = new BandMapper();
    private final JdbcTemplate jdbcTemplate;

    public JdbcSubgenreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Subgenre getSubgenreById(int subgenreId) {
        Subgenre subgenreToReturn = new Subgenre();
        String sql = "SELECT * FROM subgenres WHERE subgenre_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, subgenreId);
        if(results.next()) {
            subgenreToReturn = mapRowToSubgenre(results);
        }

        return subgenreToReturn;
    }

    @Override
    public Subgenre getSubgenreByName(String subgenreName) {
        Subgenre subgenreToReturn = new Subgenre();
        String sql = "SELECT * FROM subgenres WHERE subgenre_name = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, subgenreName);

        if(results.next()) {
            subgenreToReturn = mapRowToSubgenre(results);
        }

        return subgenreToReturn;
    }

    @Override
    public Subgenre addNewSubgenre(String subgenreName) {
        Subgenre subgenreToReturn = new Subgenre();

        String sql = "INSERT INTO subgenres (subgenre_name) VALUES (?) " +
                "RETURNING subgenre_id;";
        int subgenreId = 0;
        try {
            subgenreId = jdbcTemplate.queryForObject(sql, Integer.class, subgenreName);
            if(subgenreId != 0) {
                subgenreToReturn.setSubgenreId(subgenreId);
            }
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot create subgenre.");
        }

        return null;
    }

    @Override
    public List<Band> getBandsBySubgenre(int subgenreId) {
        String sql = "SELECT bands.* FROM bands " +
                "JOIN band_subgenres ON (bands.band_id = band_subgenres.band_id) " +
                "WHERE band_subgenres.subgenre_id = ?;";

        return jdbcTemplate.query(sql, bandMapper, subgenreId);
    }


    /*
    * TODO: Properly integrate delete methods for subgenres
    *  so that admin credentials allow subgenre management.
    * */
    @Override
    public int deleteSubgenreByName(String subgenreName) {
        return 0;
    }

    @Override
    public int deleteSubgenreById(int subgenreId) {
        return 0;
    }

    public Subgenre mapRowToSubgenre(SqlRowSet rs) {
        Subgenre subgenre = new Subgenre();
        subgenre.setSubgenreId(rs.getInt("subgenre_id"));
        subgenre.setSubgenreName(rs.getString("subgenre_name"));
        return subgenre;
    }
}
