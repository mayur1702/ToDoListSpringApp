package com.mayurmistry.Models;

import com.mayurmistry.Exceptions.InvalidUserDataException;
import com.mayurmistry.Models.Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRegistrationModel {

    private User user;
    private static final String insertUserQuery = "INSERT INTO Users(id, username, email, password) VALUES(null,?,?,?)";

    public UserRegistrationModel(User user) {
        this.user = user;
    }

    public boolean validateUserData() {
        return user.getEmail() != null && user.getPassword() != null && user.getUsername() != null;
    }

    public boolean insertQuery(PreparedStatement st) throws SQLException {
        int pointer = 0;
        st.setString(++pointer,user.getUsername());
        st.setString(++pointer,user.getEmail());
        st.setString(++pointer,user.getPassword());
        int result = st.executeUpdate();
        return  result == 1;
    }

    public void addUserToDatabase(Connection con) throws SQLException, InvalidUserDataException {
        if(!validateUserData()) {
            throw new InvalidUserDataException("Invalid user data provided");
        }
        PreparedStatement preparedStatement = con.prepareStatement(insertUserQuery);
        insertQuery(preparedStatement);
        if(preparedStatement != null) {
            preparedStatement.close();
        }

    }

}
