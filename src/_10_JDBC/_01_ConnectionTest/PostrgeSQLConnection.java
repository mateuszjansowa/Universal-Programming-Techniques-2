package _10_JDBC._01_ConnectionTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostrgeSQLConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/ksidb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("PostgreSQL connected");
        } catch (SQLException e) {
            System.out.println("Issue connecting to PostrgeSQL: " + e.getMessage());
        }
    }
}
