package com.mycompany.aula4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
  private static final String URL  = "jdbc:mysql://localhost:3306/oficina?useSSL=false&serverTimezone=UTC&characterEncoding=utf8";
  private static final String USER = "root";
  private static final String PASS = "";

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASS);
  }
}
