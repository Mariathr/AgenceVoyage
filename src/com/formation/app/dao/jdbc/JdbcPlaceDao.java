package com.formation.app.dao.jdbc;

import com.formation.app.dao.PlaceDao;
import com.formation.app.model.Place;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcPlaceDao extends JdbcDao implements PlaceDao<Long, Place> {



    @Override
    public Long createPlace(Place object) {
        String query = "INSERT INTO place (name) values (?)";
        Long insertedId = null;
        try (PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, object.getName());
            boolean isInsertRow = pst.execute();
            ResultSet keys = pst.getGeneratedKeys();
            keys.next();
            insertedId = keys.getLong(1);

            connection.commit();
        }
        catch (SQLException e1) {
            e1.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }

        }
        return insertedId;
    }

    @Override
    public Place findPlaceById(Long aLong) {
        Place place = new Place();
        String query = "SELECT * FROM place WHERE id = ? " ;
        try (PreparedStatement pst  = connection.prepareStatement(query)) {
            pst.setLong(1,aLong);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                place.setId(rs.getLong("id"));
                place.setName(rs.getString("name"));

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return place;
    }

    @Override
    public List<Place> findAllPlace() {
        List<Place> listplace = new ArrayList<>();
        String query = "SELECT * FROM place";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Place place = new Place();
                place.setId(rs.getLong("id"));
                place.setName(rs.getString("name"));
                listplace.add(place);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return listplace;
    }

    @Override
    public Boolean updatePlace(Place object) {
        int updateRows = 0;
        String query = "UPDATE place SET name = ? WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, object.getName());
            pst.setLong(2,object.getId());
            updateRows = pst.executeUpdate();
        }
        catch (SQLException e1) {
            e1.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return updateRows > 0;
    }

    @Override
    public Boolean removePlace(Long aLong) {
        boolean idDeteted = false;
        String query = "DELETE FROM  place  WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setLong(1,aLong);
            idDeteted= pst.execute();
            connection.commit();

        }
        catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }
        return idDeteted;
    }
}
