package com.techelevator.dao;

import com.techelevator.model.SharedTrip;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcSharedTripDao implements SharedTripDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcSharedTripDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addSharedTrip(SharedTrip sharedTrip) {
        String sql = "INSERT INTO shared_trips (trip_id, user1_id, user2_id) VALUES (?,?,?)";
        jdbcTemplate.update(sql, sharedTrip.getTripId(), sharedTrip.getUser1Id(), sharedTrip.getUser2Id());
    }
}
