package org.test;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class MainTest {

    public static void main(String[] args) throws SQLException {
        DBWorker dbWorker = new DBWorker();

        String query = "SELECT * FROM users";

        try {
            Statement statement = dbWorker.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));

                System.out.println(user);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
