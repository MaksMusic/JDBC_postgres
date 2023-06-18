package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    private final String url;
    private final String userName;
    private final String password;


    //принимаем данный для коннекта к бд
    public DatabaseConnectionManager(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    //возвращаем Connect на основе данных для подключения
    public Connection getConnection() throws SQLException {
        return  DriverManager.getConnection(url, userName, password);
    }
}
