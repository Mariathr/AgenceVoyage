package com.formation.app.dao.jdbc;

import com.formation.app.util.ConnectionManager;

import java.sql.Connection;

public abstract class JdbcDao  {
  protected Connection connection;

  public JdbcDao() {
    this.connection = ConnectionManager.getConnection();

  }
}
