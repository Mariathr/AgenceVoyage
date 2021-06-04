package com.formation.app.dao.jdbc;

import com.formation.app.dao.PlaceDao;
import com.formation.app.model.Place;

import java.util.List;

public class JdbcPlaceDao extends JdbcDao implements PlaceDao<Long, Place> {
    @Override
    public Long createPlace(Place object) {
        return null;
    }

    @Override
    public Place findPlaceById(Long aLong) {
        return null;
    }

    @Override
    public List<Place> findAllPlace() {
        return null;
    }

    @Override
    public Boolean updatePlace(Place object) {
        return null;
    }

    @Override
    public Boolean removePlace(Long aLong) {
        return null;
    }
}
