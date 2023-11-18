package org.example;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false";
        String user = "root";
        String password = "root";

        Driver drive = new com.mysql.jdbc.Driver();
        DriverManager.registerDriver(drive);

        try (Connection conn =  DriverManager.getConnection(url, user, password)){
            Statement statement = conn.createStatement();

            statement.execute("""   
                create table user(
                id integer primary key auto_increment, name varchar(100))
                """);

            String sql = "INSERT INTO user (id, name) Values(?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, 3);
            preparedStatement.setString(1, "Ivan");
            preparedStatement.execute();

            ResultSet set = preparedStatement.executeQuery("select * from user");

            while(set.next()){
                System.out.println(set.getInt("id") + " " + set.getString("name"));
            }

            conn.setAutoCommit(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}