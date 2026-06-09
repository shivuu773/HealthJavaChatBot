package com.healthcare.db;

import java.sql.*;
import java.io.*;

public class DatabaseConnection {
    private static final String DB_PATH = System.getProperty("user.home") + "/healthcare_chatbot.db";
    private static final String URL = "jdbc:sqlite:" + DB_PATH;
    private static Connection connection = null;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL);
            connection.setAutoCommit(true);
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
