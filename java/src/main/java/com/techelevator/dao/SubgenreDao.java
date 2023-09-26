package com.techelevator.dao;

import com.techelevator.model.Subgenre;

public interface SubgenreDao {
    Subgenre getSubgenreById(int subgenreId);
    Subgenre getSubgenreByName(String subgenreName);
    Subgenre addNewSubgenre(String subgenreName);
    int deleteSubgenreByName(String subgenreName);
    int deleteSubgenreById(int subgenreId);
}
