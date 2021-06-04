package com.formation.app.dao;

import com.formation.app.dao.jdbc.JdbcPlaceDao;
import com.formation.app.dao.jdbc.JdbcTripDao;
import com.formation.app.model.Place;
import com.formation.app.model.Trip;

import java.util.List;

public class DaoFactory  {
    public DaoFactory(){

    }

   public static JdbcTripDao GetTripDao() {
       JdbcTripDao jdbcTripDao = new JdbcTripDao();
       return jdbcTripDao;
   }
   public static JdbcPlaceDao  GetPlaceDao(){
       JdbcPlaceDao jdbcPlaceDao = new JdbcPlaceDao();
       return jdbcPlaceDao;
   }

}
