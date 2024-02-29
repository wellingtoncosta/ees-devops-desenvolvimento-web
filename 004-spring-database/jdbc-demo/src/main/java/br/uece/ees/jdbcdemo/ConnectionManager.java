package br.uece.ees.jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum ConnectionManager {
    INSTANCE;

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/eesdevops";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "postgres";

    public void checkDriver() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
}
