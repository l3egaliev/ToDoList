package kg.rakhim.classes.dao;


import kg.rakhim.classes.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }



    public List<Task> listTasks(){
        return jdbcTemplate.query("Select * from task", new BeanPropertyRowMapper<>(Task.class));
    }

    public void addTask(Task task) {

        jdbcTemplate.update("INSERT INTO task(name) values (?)", task.getName());

    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM task WHERE id = ?", id);
    }
}
