package com.techelevator.dao;

import com.techelevator.model.Itinerary;
import com.techelevator.model.Landmark;
import com.techelevator.model.Trip;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
@Component

public class JdbcItineraryDao implements ItineraryDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcItineraryDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int getUserIdByUsername(String username) {
        int userId = 0;
        String sql = "SELECT user_id FROM users WHERE username = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
        if (result.next()) {
            userId = result.getInt("user_id");
        } return userId;
    }

    @Override
    public void createNewItinerary(Itinerary newItinerary, String username) {
        String sql = "INSERT INTO itinerary (itinerary_name, itinerary_date, itinerary_starting_point) VALUES (?,?,?) RETURNING itinerary_id";
        try{
            int newItineraryId = jdbcTemplate.queryForObject(sql, int.class, newItinerary.getItineraryName(), newItinerary.getItineraryDate(), newItinerary.getItineraryStartingPoint());
            newItinerary.setItineraryId(newItineraryId);

            String sqlForTripTable = "INSERT INTO trips (user_id, itinerary_id) VALUES (?,?)";
            int userId = getUserIdByUsername(username);
            jdbcTemplate.update(sqlForTripTable, userId, newItineraryId);
        }
        catch (CannotGetJdbcConnectionException e){
            throw new RuntimeException();
        }
//        catch (Exception e){
//            throw new RuntimeException();
//        }
    }

    @Override
    public void deleteItinerary(int id) {
        String sql = "DELETE FROM itinerary WHERE itinerary_id = ?";
        try{
            jdbcTemplate.update(sql, id);
        }
        catch (CannotGetJdbcConnectionException e){
            throw new RuntimeException();
        }
    }

    @Override
    public List<Itinerary> getAllItineraries(int userId) {
        List <Itinerary> resultList = new ArrayList<>();
        String sql = "SELECT * FROM itinerary " +

                " JOIN trips ON itinerary.itinerary_id = trips.itinerary_id" +

                " WHERE trips.user_id = ?";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
              resultList.add(mapRowToItinerary(results));
            }
            return resultList;
        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public void updateItinerary(Itinerary newItinerary) {
        String sqlUpdateItinerary = "UPDATE itinerary" +
                " SET itinerary_name = ?," +
                " itinerary_date = ?," +
                " itinerary_starting_point = ?" +
                " WHERE itinerary_id = ?";
        jdbcTemplate.update(sqlUpdateItinerary, newItinerary.getItineraryName(), newItinerary.getItineraryDate(), newItinerary.getItineraryStartingPoint(), newItinerary.getItineraryId());
    }

    @Override
    public Itinerary getSpecificItinerary(int itineraryId) {
        String getItinerarySql = "SELECT * FROM itinerary WHERE itinerary_id = ?";
        Itinerary specificItinerary = new Itinerary();
        SqlRowSet results = jdbcTemplate.queryForRowSet(getItinerarySql, itineraryId);

        try {
            if (results.next()) {
                specificItinerary = mapRowToItinerary(results);
            }
        } catch (Exception e){
            throw new RuntimeException();
        }
        return specificItinerary;
    }
    public List<Itinerary> getAllItinerariesByUserName(String userName) {
        int userId = getUserIdByUsername(userName);
        List <Itinerary> resultList = new ArrayList<>();
        String sql = "SELECT * FROM itinerary " +

                " JOIN trips ON itinerary.itinerary_id = trips.itinerary_id" +

                " WHERE trips.user_id = ?";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                resultList.add(mapRowToItinerary(results));
            }
            return resultList;
        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    public Itinerary mapRowToItinerary(SqlRowSet rowSet) {
        Itinerary itinerary = new Itinerary();
        itinerary.setItineraryId(rowSet.getInt("itinerary_id"));
        itinerary.setItineraryName(rowSet.getString("itinerary_name"));
        itinerary.setItineraryDate(rowSet.getDate("itinerary_date").toLocalDate());
        itinerary.setItineraryStartingPoint(rowSet.getString("itinerary_starting_point"));
        return itinerary;
    }
}
