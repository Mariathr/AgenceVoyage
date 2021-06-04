package com.formation.app.dao.jdbc;

import com.formation.app.dao.PlaceDao;
import com.formation.app.dao.TripDao;
import com.formation.app.model.Place;
import com.formation.app.model.Trip;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcTripDao<ID,T> extends JdbcDao implements TripDao<Long, Trip> {

    @Override
    public Long createTrip(Trip object) {
        String query = "INSERT INTO trip (idDepart,idArrivee,prix) values (?,?,?)";
        Long insertedId = null;
        try (PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pst.setLong(1, object.getIdArrivee());
            pst.setLong(2, object.getIdDepart());
            pst.setFloat(3, object.getPrix());
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
    public Trip findTripById(Long aLong) {
        Trip trip = new Trip();
        String query = "SELECT trip.id, trip.idArrivee,t2.name as nomArrivee, trip.idDepart, t1.name as nomDepart, trip.prix" +
                " FROM agence.trip " +
                " JOIN agence.place t1 on trip.idDepart = t1.id" +
                " JOIN agence.place t2 on trip.idArrivee = t2.id  WHERE trip.id = ? " ;
        try (PreparedStatement pst  = connection.prepareStatement(query)) {
            pst.setLong(1,aLong);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                trip.setId(rs.getLong("id"));
                trip.setIdArrivee(rs.getLong("idArrivee"));
                trip.setIdDepart(rs.getLong("idDepart"));
                trip.setPrix(rs.getFloat("prix"));
                trip.setNomArrivee(rs.getString("nomArrivee"));
                trip.setNomDepart(rs.getString("nomDepart"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return trip;
    }

    @Override
    public List<Trip> findAllTrip() {
        List<Trip> listtrip = new ArrayList<>();
        String query = "SELECT * FROM trip";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Trip trip = new Trip();
                trip.setId(rs.getLong("id"));
                trip.setIdArrivee(rs.getLong("idArrivee"));
                trip.setIdDepart(rs.getLong("idDepart"));
                trip.setPrix(rs.getFloat("prix"));
                listtrip.add(trip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listtrip;
    }

    @Override
    public Boolean updateTrip(Trip object) {
        int updateRows = 0;
        String query = "UPDATE trip SET idArrivee = ?,idDepart =?, prix=? WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setLong(1, object.getIdArrivee());
            pst.setLong(2, object.getIdDepart());
            pst.setFloat(3, object.getPrix());
            pst.setLong(4,object.getId());
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
    public Boolean removeTrip(Long aLong) {
        boolean idDeteted = false;
        String query = "DELETE FROM  trip  WHERE id = ?";
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
