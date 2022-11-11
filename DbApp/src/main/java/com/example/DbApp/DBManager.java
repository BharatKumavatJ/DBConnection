package com.example.DbApp;

import org.springframework.stereotype.Component;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class DBManager {
    public static Connection connection;
    public static Statement stmt;

    public DBManager() throws SQLException, ClassNotFoundException {
        if(connection == null){
           // Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/UserDB","root","redhat");

        }
        stmt=connection.createStatement();
        createTable();

    }


    public void createTable() throws SQLException{
            String sql = "create table IF NOT EXISTS userTable  (id INT AUTO_INCREMENT PRIMARY KEY, name varchar(20), age INT);";
            stmt.execute(sql);

    }


    public void insertInfo(User user) throws SQLException {
        String sql = "insert into UserTable (name, age) values('"+user.getName()+"', "+user.getAge()+")";
        int row = stmt.executeUpdate(sql); // insert update delete
        System.out.println(row + "affected");

    }

    public List<String> getInfo() throws SQLException {
        String sql = "select * from UserTable";
        ResultSet resultSet =  stmt.executeQuery(sql);

        List<String> userList = new ArrayList<>();

        while(resultSet.next())
        {
            String name = resultSet.getString(2);
            userList.add(name);
        }
        return userList;
    }

    public void updateUserName(String name) throws SQLException{
        String sql = "update userTable set name = 'bharatKumar' where name = 'bharat'";
        int rowAffected = stmt.executeUpdate(sql);
        System.out.println(rowAffected);
    }

}
