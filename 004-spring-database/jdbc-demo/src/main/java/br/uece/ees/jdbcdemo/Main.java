package br.uece.ees.jdbcdemo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            checkDriver();
            insertData();
            printResults();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void checkDriver() throws ClassNotFoundException {
        System.out.println("Checking Jdbc driver...");
        ConnectionManager.INSTANCE.checkDriver();
        System.out.println("Driver loaded!");
    }

    private static void insertData() throws SQLException {
        System.out.println("Starting data insertion...");
        try (Connection connection = ConnectionManager.INSTANCE.connect()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("""
                INSERT INTO bills (title, amount_due, due_date)
                VALUES ('Energia', 194.90, '2024-03-20')"""
            );
            statement.executeUpdate("""
                INSERT INTO bills (title, amount_due, due_date)
                VALUES ('Agua', 56.68, '2024-03-21')"""
            );
            statement.executeUpdate("""
                INSERT INTO bills (title, amount_due, due_date)
                VALUES ('Internet', 99.90, '2024-03-22')"""
            );
        }
        System.out.println("Done!");
    }

    private static void printResults() throws SQLException {
        System.out.println("Fetching data...");
        try (Connection connection = ConnectionManager.INSTANCE.connect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bills");
            while (resultSet.next()) {
                System.out.print("Id: " + resultSet.getInt("id"));
                System.out.print(", Title: " + resultSet.getString("title"));
                System.out.print(", Amount due: " + resultSet.getBigDecimal("amount_due").stripTrailingZeros());
                System.out.print(", Due date: " + resultSet.getDate("due_date"));
                System.out.print(", Created at: " + resultSet.getTimestamp("created_at"));
                System.out.println("\n--------------------------------------------------");
            }
        }
        System.out.println("Done!");
    }
}
