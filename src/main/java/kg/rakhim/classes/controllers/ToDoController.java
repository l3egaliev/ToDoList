package kg.rakhim.classes.controllers;

import jakarta.validation.Valid;
import kg.rakhim.classes.dao.TaskDAO;
import kg.rakhim.classes.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/list")
public class ToDoController {
    private TaskDAO taskDAO;

    @Autowired
    ToDoController(TaskDAO taskDAO){
        this.taskDAO = taskDAO;
    }

    @GetMapping
    public String list(Model model, @ModelAttribute("newTask") Task task){
        model.addAttribute("tasks", taskDAO.listTasks());
        return "list/index";
    }


    @PostMapping()
    public String addTask(@ModelAttribute("newTask") @Valid Task task, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "list/index";

        taskDAO.addTask(task);
        return "redirect:/list";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id){
        taskDAO.delete(id);
        return "redirect:/list";
    }
}
