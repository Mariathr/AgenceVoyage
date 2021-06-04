package com.formation.app.dao;

import java.util.List;

public interface TripDao<ID,T> {
    Long createTrip(T object);

    T findTripById(ID id);

    List<T> findAllTrip();

    Boolean updateTrip(T object);

    Boolean removeTrip(ID id);
}
