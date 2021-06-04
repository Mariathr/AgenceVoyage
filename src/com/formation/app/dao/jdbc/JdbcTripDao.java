package com.formation.app.dao.jdbc;

import com.formation.app.dao.PlaceDao;
import com.formation.app.dao.TripDao;
import com.formation.app.model.Place;
import com.formation.app.model.Trip;

import java.util.List;

public class JdbcTripDao extends JdbcDao implements TripDao<Long, Trip> {

    @Override
    public Long createTrip(Trip object) {
        return null;
    }

    @Override
    public Trip findTripById(Long aLong) {
        return null;
    }

    @Override
    public List<Trip> findAllTrip() {
        return null;
    }

    @Override
    public Boolean updateTrip(Trip object) {
        return null;
    }

    @Override
    public Boolean removeTrip(Long aLong) {
        return null;
    }
}
