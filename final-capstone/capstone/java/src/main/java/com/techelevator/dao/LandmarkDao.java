package com.techelevator.dao;

import com.techelevator.model.Landmark;

import java.util.List;

public interface LandmarkDao {

    List<Landmark> getAllLandmarks();
    List<Landmark> getLandmarksOnItinerary(int itineraryId);
    Landmark getSpecificLandmarkById(int id);
    void addNewLandmark(Landmark newLandmark, String username);
    void addLikeToLandmark(int landmark_id);
    void removeALikeToLandmark(int landmark_id);

}
