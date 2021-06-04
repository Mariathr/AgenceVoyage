package com.formation.app.dao;

import java.util.List;

public interface PlaceDao<ID,T> {
    Long createPlace(T object);

    T findPlaceById(ID id);

    List<T> findAllPlace();

    Boolean updatePlace(T object);

    Boolean removePlace(ID id);
}
