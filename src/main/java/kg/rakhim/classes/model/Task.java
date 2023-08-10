package kg.rakhim.classes.model;


import jakarta.validation.constraints.*;
import org.springframework.stereotype.Component;

public class Task {
    private int id;


    @NotEmpty(message = "Название задачи должно начаться хотя бы от 4 символов!")
    @Size(min=4, message = "Минимум 4 символа")
    @NotBlank(message = "Символы!")
    private String name;


    public Task() {
    }

    public Task(int id, String task_name) {
        this.id = id;
        this.name = task_name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
