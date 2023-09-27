package com.techelevator.dao;

import com.techelevator.model.Band;
import com.techelevator.model.Subgenre;

import java.util.List;

public interface SubgenreDao {

    Subgenre getSubgenreById(int subgenreId);
    Subgenre getSubgenreByName(String subgenreName);
    List<Band> getBandsBySubgenre(int subgenreId);
    Subgenre addNewSubgenre(String subgenreName);
    int deleteSubgenreByName(String subgenreName);
    int deleteSubgenreById(int subgenreId);
}
