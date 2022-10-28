package com.labs.complex.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class DBConnection {
    private static DBConnection dbConnection;

    private Connection connection;

    private DBConnection() {
        String url = getURL();
        try {
            connection = DriverManager.getConnection(url);
        }
        catch (SQLException exception) {
            System.out.println("Can't connect to database due to restricted access or null url.");
        }
    }

    public static DBConnection getInstance() {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    private String getURL() {
        try {
            Scanner scanner = new Scanner(new File("url.txt"));
            return scanner.next();
        }
        catch (FileNotFoundException exception) {
            System.out.println("File with url was not found.");
        }
        return null;
    }

    @Override
    public String toString() {
        return "DBConnection{" +
                "connection=" + connection +
                '}';
    }

    public void close() throws SQLException {
        connection.close();
    }

    public Statement createStatement() {
        try {
            return connection.createStatement();
        }
        catch (SQLException exception) {
            System.out.println("Can't create SQL statement");
        }
        return null;
    }

    public PreparedStatement prepareStatement(String query) {
        try {
            return connection.prepareStatement(query);
        }
        catch (SQLException exception) {
            System.out.println("Can't create SQL statement");
        }
        return null;
    }
}
