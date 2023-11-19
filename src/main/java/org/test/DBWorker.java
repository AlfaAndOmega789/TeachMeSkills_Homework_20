package org.test;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWorker  {
    private static final String URL = "jdbc:mysql://localhost:3306/userstestdb?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection connection;

    public DBWorker() throws SQLException {
        connection =  DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            Statement statement = connection.createStatement();
    }
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
