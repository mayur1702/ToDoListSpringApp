package com.mayurmistry.Controllers;

import com.mayurmistry.Dao.DbUtils;
import com.mayurmistry.Models.Entity.Response;
import com.mayurmistry.Models.Entity.User;
import com.mayurmistry.Models.UserLoginModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping
    public Response authenticateUser(@RequestBody User user, HttpServletResponse res) {
        UserLoginModel userLoginModel = new UserLoginModel(user);
        Response response = new Response();
        Connection con = DbUtils.getDbConnection();
        try {
            boolean validUser = userLoginModel.authenticateUser(con);
            if(validUser) {
                res.addCookie(new Cookie("token",user.getUsername()));
                response.setStatus(true);
                response.setBody("Login Successful");
            } else {
                response.setStatus(false);
                response.setExceptionMessage("Invalid credentials provided");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            response.setStatus(false);
            response.setExceptionMessage("Cannot authenticate user, try again in sometime");
            response.setErrorCode(500);
        } finally {
            DbUtils.closeConnection();
            return response;
        }
    }
}
