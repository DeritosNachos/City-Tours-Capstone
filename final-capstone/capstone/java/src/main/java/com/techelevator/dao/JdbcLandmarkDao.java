package com.techelevator.dao;

import com.techelevator.model.Itinerary;
import com.techelevator.model.Landmark;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcLandmarkDao implements LandmarkDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcLandmarkDao(DataSource dataSource) {

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Landmark> getAllLandmarks() {
        List<Landmark> listOfLandmarks = new ArrayList<>();
        String sql = "SELECT * FROM landmarks";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        try{
            while (results.next()) {
//                int id = results.getInt("landmark_id");
//                String name = results.getString("landmark_name");
//                String photo = results.getString("landmark_photo");
//                String description = results.getString("landmark_description");
//                String address = results.getString("landmark_address");
                Landmark landmark = mapRowLandmark(results);
//                Landmark newLandmark = new Landmark(id, name, photo, description, address);
                listOfLandmarks.add(landmark);
            }
        }
        catch (CannotGetJdbcConnectionException e){
            throw new RuntimeException();
        }

        return listOfLandmarks;
    }

    @Override
    public List<Landmark> getLandmarksOnItinerary(int itineraryId) {
        List<Landmark> resultList = new ArrayList<>();
        String sql = "Select * FROM landmarks as l" +
                " JOIN destinations as d ON l.landmark_id = d.landmark_id" +
                " WHERE d.itinerary_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, itineraryId);
            while (results.next()) {
                resultList.add(mapRowLandmark(results));
            }
            return resultList;
        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public Landmark getSpecificLandmarkById(int id) {
        Landmark landmark = new Landmark();
        String sql = "SELECT * FROM landmarks WHERE landmark_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        try {
            if (results.next()) {
                landmark = mapRowLandmark(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException();
        } return landmark;
    }

    @Override
    public void addNewLandmark(Landmark newLandmark, String username) {
        String sql = "INSERT INTO landmarks (landmark_name, landmark_photo, landmark_description, landmark_address, landmark_hours_of_operation, landmark_venue_type) VALUES (?,?,?,?,?,?) RETURNING landmark_id";
        try{
            int newLandmarkId = jdbcTemplate.queryForObject(sql, int.class, newLandmark.getLandmarkName(), newLandmark.getLandmarkPhoto(), newLandmark.getLandmarkDescription(), newLandmark.getLandmarkAddress(), newLandmark.getLandmarkHoursOfOperation(), newLandmark.getLandmarkVenueType());
            newLandmark.setLandmarkId(newLandmarkId);


        }
        catch (CannotGetJdbcConnectionException e){
            throw new RuntimeException();
        }
    }

    @Override
    public void addLikeToLandmark(int landmark_id) {
        String addLike = "UPDATE landmarks" +
                " SET landmark_like_count = landmark_like_count + 1" +
                " WHERE landmark_id = ?";
        jdbcTemplate.update(addLike, landmark_id);
    }

    @Override
    public void removeALikeToLandmark(int landmark_id) {
        String deleteLike = "UPDATE landmarks" +
                " SET landmark_like_count = landmark_like_count - 1" +
                " WHERE landmark_id = ?";
        jdbcTemplate.update(deleteLike, landmark_id);
    }




    private Landmark mapRowLandmark(SqlRowSet rs){
        Landmark landmark = new Landmark();
        landmark.setLandmarkId(rs.getInt( "landmark_id"));
        landmark.setLandmarkName(rs.getString("landmark_name"));
        landmark.setLandmarkPhoto(rs.getString("landmark_photo"));
        landmark.setLandmarkDescription(rs.getString("landmark_description"));
        landmark.setLandmarkAddress(rs.getString("landmark_address"));
        landmark.setLandmarkHoursOfOperation(rs.getString("landmark_hours_of_operation"));
        landmark.setLandmarkVenueType(rs.getString("landmark_venue_type"));
        return landmark;
    }
}
