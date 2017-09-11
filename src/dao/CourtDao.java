package dao;

import pojo.Court;

import java.util.List;


public interface CourtDao {
    List<Court> getCourtByName(String courtName);

    void addCourt(Court court);

    void updateCourt(Court court);

    Court getCertainCourt(Court court);

}
