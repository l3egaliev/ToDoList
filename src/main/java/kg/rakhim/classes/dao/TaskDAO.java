package kg.rakhim.classes.dao;


import kg.rakhim.classes.model.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskDAO {
    private List<Task> tasks = new ArrayList<>();
    private int count;


    {
        tasks.add(new Task(++count, "Create new To Do List App"));
        tasks.add(new Task(++count, "Sleep"));
    }

    public List<Task> listTasks(){
        return tasks;
    }

    public void addTask(Task task) {
        task.setId(++count);
        tasks.add(task);
    }

    public void delete(int id){
        tasks.removeIf(t -> t.getId() == id);
    }
}
