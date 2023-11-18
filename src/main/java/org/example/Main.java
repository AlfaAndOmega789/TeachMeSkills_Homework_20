package org.example;

import java.sql.*;

import com.mysql.cj.jdbc.Driver;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) throws SQLException {

        Driver driver = new Driver();
        DriverManager.registerDriver(driver);

        try (Connection conn =  DriverManager.getConnection(URL, USERNAME, PASSWORD)){
            Statement statement = conn.createStatement();

//            statement.execute("""
//                create table users(
//                id integer primary key auto_increment, name varchar(100))
//                """);

            String sql = "INSERT INTO user (id, name) Values(?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, 2);
            preparedStatement.setString(2, "Ivan");
            preparedStatement.execute();

            ResultSet set = preparedStatement.executeQuery("select * from user");

            while(set.next()){
                System.out.println(set.getInt("id") + " " + set.getString("name"));
            }

            conn.setAutoCommit(true);
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Неудалось загрузить класс драйвер");
        }
    }
}