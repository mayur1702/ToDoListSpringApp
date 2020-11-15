package com.mayurmistry.Models;

import com.mayurmistry.Models.Entity.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserTasksModel {

    private static final String getAllTasks = "SELECT * FROM UserTasks WHERE email=?";
    private static final String addNewTaskQuery = "INSERT INTO UserTasks(email, task, dueDate, completed, status) VALUES(?,?,?,'0','active')";

    public List<Task> createTaskObject(ResultSet resultSet, List<Task> taskArrayList) throws SQLException {
        while(resultSet.next()) {
            Task task = new Task();
            task.setEmail(resultSet.getString("email"));
            task.setTask(resultSet.getString("task"));
            task.setCreatedOn(resultSet.getString("createdOn"));
            task.setDueDate(resultSet.getString("dueDate"));
            task.setCompleted(resultSet.getString("completed"));
            task.setUpdatedOn(resultSet.getString("updatedOn"));
            task.setStatus(resultSet.getString("status"));
            task.setId(resultSet.getInt("id"));
            taskArrayList.add(task);
        }
        return taskArrayList;
    }

    public List<Task> getUserTasks(Connection con, String email, String type) throws SQLException {
        PreparedStatement preparedStatement = null;
        List<Task> taskArrayList = new ArrayList<>();
        try {
            preparedStatement = con.prepareStatement(getAllTasks);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            createTaskObject(resultSet,taskArrayList);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if(preparedStatement != null) {
                preparedStatement.close();
            }
            return taskArrayList;
        }
    }

    public boolean addNewTask(Connection con, Task task) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement(addNewTaskQuery);;
        boolean status = false;
        int pointer = 0;
        preparedStatement.setString(++pointer, task.getEmail());
        preparedStatement.setString(++pointer, task.getTask());
        preparedStatement.setString(++pointer, task.getDueDate());
        int result = preparedStatement.executeUpdate();
        if(result == 1) {
            status = true;
        }
        if(preparedStatement != null) {
            preparedStatement.close();
        }
        return status;

    }
}
