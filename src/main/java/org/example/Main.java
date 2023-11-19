package org.example;

import java.sql.*;

import com.mysql.cj.jdbc.Driver;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) throws SQLException {

        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        try (Connection conn =  DriverManager.getConnection(URL, USERNAME, PASSWORD)){
            Statement statement = conn.createStatement();

//            statement.execute("""
//                create table users(
//                id integer primary key auto_increment, name varchar(100))
//                """);

//            String sql = "INSERT INTO user (id, name) Values(?, ?)";
//            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//            preparedStatement.setInt(2, 2);
//            preparedStatement.setString(2, "Ivan");
//            preparedStatement.execute();

            int res = statement.executeUpdate("UPDATE users SET name = 'NEW NAME' WHERE ID = 3;");
            System.out.println(res);

            int res1 = statement.executeUpdate("DELETE FROM users WHERE id  = 5");
            System.out.println(res1);

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

//            System.out.println(res);
//            ResultSet set = preparedStatement.executeQuery("select * from user");

//            while(set.next()){
//                System.out.println(set.getInt("id") + " " + set.getString("name"));
//            }

//            conn.setAutoCommit(true);
            conn.close();

            if(conn.isClosed()){
                System.out.println("Соединение с БД закрыто!");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Неудалось загрузить класс драйвер");
        }
    }
}