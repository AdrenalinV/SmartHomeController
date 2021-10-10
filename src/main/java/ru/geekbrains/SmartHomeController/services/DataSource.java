package ru.geekbrains.SmartHomeController.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private static final String URL="jdbc:h2:file:./database;MODE=PostgreSQL";

    static {
        try{
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Database Driver initialization Error");
        }
    }

    private DataSource(){}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,"sa","");
    }
}
