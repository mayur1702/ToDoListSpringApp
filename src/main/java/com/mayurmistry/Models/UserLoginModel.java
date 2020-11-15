package com.mayurmistry.Models;

import com.mayurmistry.Models.Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoginModel {
    private User user = null;
    private static final String getUserQuery = "SELECT * FROM Users WHERE email=? and password=?";

    public UserLoginModel(User user) {
        this.user = user;
    }

    public boolean authenticateUser(Connection con) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement(getUserQuery);
        int pointer = 0;
        preparedStatement.setString(++pointer,user.getEmail());
        preparedStatement.setString(++pointer,user.getPassword());
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            user.setUsername(resultSet.getString("username"));
            return true;
        }
        return false;
    }

}
