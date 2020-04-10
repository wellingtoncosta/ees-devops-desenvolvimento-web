package br.uece.eesdevop.bancodedados.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcExample {

    public static void main(String[] args) {
        try {
            String url = "jdbc:postgresql://localhost:5432/eesdevops";
            String username = "postgres";
            String password = "postgres";

            Connection connection = DriverManager.getConnection(url, username, password);

            System.out.println("Connected!");

            connection.close();
        } catch (SQLException exception) {
            System.err.println("Unable to connect to database due error: " + exception.getMessage());
        }
    }

}

