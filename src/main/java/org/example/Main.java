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

            String sql = "INSERT INTO users (id, name, age, email) Values(?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, 11 );
            preparedStatement.setString(2, "Ivan");
            preparedStatement.setInt(3, 21 );
            preparedStatement.setString(4, "vanya.koval1998@gmail.com");
            preparedStatement.execute();

            int res = statement.executeUpdate("UPDATE users SET name = 'NEW NAME' WHERE ID = 3;");
            System.out.println(res);

            int res1 = statement.executeUpdate("DELETE FROM users WHERE id  = 5");
            System.out.println(res1);

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

//            statement.addBatch("INSERT INTO users(id, name, email) VALUES (10, 'Sergei', 'vanya.koval1998@gmail.com')");
//            statement.executeBatch();
//            statement.clearBatch();

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