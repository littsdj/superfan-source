package com.techelevator.controller;

import com.techelevator.dao.SubgenreDao;
import com.techelevator.model.Band;
import com.techelevator.model.Subgenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@PreAuthorize("isAuthenticated()")
public class SubgenreController {

    @Autowired
    private SubgenreDao subgenreDao;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public Subgenre getSubgenreByName(@PathVariable String subgenreName) {
        return subgenreDao.getSubgenreByName(subgenreName);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Band> getBandsBySubgenre(@PathVariable int subgenreId) {
        return subgenreDao.getBandsBySubgenre(subgenreId);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public Subgenre addNewSubgenre(@RequestBody String subgenreName) {
        return subgenreDao.addNewSubgenre(subgenreName);
    }
}
