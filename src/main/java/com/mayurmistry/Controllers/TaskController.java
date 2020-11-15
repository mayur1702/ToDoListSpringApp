package com.mayurmistry.Controllers;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.mayurmistry.Dao.DbUtils;
import com.mayurmistry.Models.Entity.Response;
import com.mayurmistry.Models.Entity.Task;
import com.mayurmistry.Models.UserTasksModel;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/tasks/")
public class TaskController {

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public Response getUserTasks(@RequestParam String type, HttpServletRequest request) {
        Response response = new Response();
        Connection con = DbUtils.getDbConnection();
        UserTasksModel tasksModel = new UserTasksModel();
        try {
            List<Task> tasks = tasksModel.getUserTasks(con,"m@m.com",null);
            JSONWrappedObject data = new JSONWrappedObject(null,null,tasks);
            response.setData(data);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (Exception ex) {
            response.setErrorCode(500);
            response.setExceptionMessage("Something went wrong");
        } finally {
            DbUtils.closeConnection();
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public Response addUserTask(@RequestBody Task task) {
        Response response = new Response();
        Connection con = DbUtils.getDbConnection();
        UserTasksModel tasksModel = new UserTasksModel();
        try {
            boolean status = tasksModel.addNewTask(con,task);
            if(status) {
                response.setStatus(true);
                response.setBody("Task added successfully");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            response.setStatus(false);
            response.setExceptionMessage("Error adding task");
        } catch (Exception e) {
            response.setErrorCode(500);
            response.setStatus(false);
            response.setExceptionMessage("Something went wrong");
        } finally {
            DbUtils.closeConnection();
        }
        return response;
    }
}
