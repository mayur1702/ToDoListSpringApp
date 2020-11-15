package com.mayurmistry.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {

    private static final String url = "jdbc:mysql://localhost:3306/ToDoList";
    private static final String username = "root";
    private static final String password = "password";
    private static final String driverurl = "com.mysql.cj.jdbc.Driver";
    private static Connection con = null;

    public static Connection getDbConnection() {
        try {
            Class.forName(driverurl);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(url,username,password);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return con;
    }

    public static void closeConnection() {
        try {
            con.close();
        } catch (SQLException exception) {
//            exception.printStackTrace();
        }
    }
}
