package com.mayurmistry.Controllers;

import com.mayurmistry.Dao.DbUtils;
import com.mayurmistry.Exceptions.InvalidUserDataException;
import com.mayurmistry.Models.Entity.Response;
import com.mayurmistry.Models.Entity.User;
import com.mayurmistry.Models.UserRegistrationModel;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;

@RestController
@RequestMapping("/register")
@ResponseBody
public class RegistrationController {

    @PostMapping
    public Response registerUser(@RequestBody User user) {
        Connection con = DbUtils.getDbConnection();
        Response response = new Response();
        UserRegistrationModel userModel = new UserRegistrationModel(user);
        try {
            userModel.addUserToDatabase(con);
            response.setStatus(true);
            response.setBody("User Registered Successfully");
        } catch (SQLException exception) {
            exception.printStackTrace();
            response.setStatus(false);
            response.setErrorCode(500);
            response.setExceptionMessage("Error adding new user");
        } catch (InvalidUserDataException exception) {
            response.setStatus(false);
            response.setErrorCode(400);
            response.setExceptionMessage("Invalid user data provided");
        } finally {
            DbUtils.closeConnection();
            return response;
        }
    }
}
