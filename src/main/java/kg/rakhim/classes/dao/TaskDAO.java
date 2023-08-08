package kg.rakhim.classes.dao;


import kg.rakhim.classes.model.Task;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskDAO {

    private static String url = "jdbc:mysql://localhost:3306/todo_list";
    private static String user = "root";
    private static String password = "testtest";

    private static Connection connection;


    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
          connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public List<Task> listTasks(){
        List<Task> tasks = new ArrayList<>();

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM task");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Task task = new Task();
                task.setName(resultSet.getString("task_name"));

                tasks.add(task);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tasks;
    }

    public void addTask(Task task) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO task (task_name) values (?)");

            preparedStatement.setString(1, task.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void delete(String name) {
        try{
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM task WHERE task_name = ?");

            preparedStatement.setString(1, name);

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
