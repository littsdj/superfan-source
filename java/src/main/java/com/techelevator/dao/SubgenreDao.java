package com.techelevator.dao;

import com.techelevator.model.Band;
import com.techelevator.model.Subgenre;

import java.util.List;

public interface SubgenreDao {

    Subgenre getSubgenreById(int subgenreId);
    Subgenre getSubgenreByName(String subgenreName);
    List<Band> getBandsBySubgenre(int subgenreId);
    Subgenre addNewSubgenre(String subgenreName);
    boolean deleteSubgenreByName(String subgenreName);
    boolean deleteSubgenreById(int subgenreId);
}
