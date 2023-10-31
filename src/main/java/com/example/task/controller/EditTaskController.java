package com.example.task.controller;

import com.example.task.entity.Task;
import com.example.task.repository.TaskRepository;
import com.example.task.service.EditTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class EditTaskController {
    private EditTaskService editTaskService;
    private TaskRepository taskRepository;
     @Autowired
    public EditTaskController(EditTaskService editTaskService,TaskRepository taskRepository ) {
        this.editTaskService = editTaskService;
        this.taskRepository = taskRepository;
    }




    @GetMapping("/editTask/{taskId}")
    public String getEditTaskPage(@PathVariable("taskId") Integer taskId, Model model){
       Task existingTask = taskRepository.findTaskById(taskId).orElse(null);
       if (existingTask == null){
           return "errorPage";
       }
       model.addAttribute("editRequest", existingTask);
       return "EditTask";
    }


    @PostMapping("/editTask/{taskId}")
    public String updateTask(@PathVariable("taskId") Integer taskId, @ModelAttribute Task task){
         Task updatedTask = editTaskService
                 .updateTask(taskId,task.getTitle(),task.getDescription());
         return updatedTask == null ? "errorPage" : "taskUpdatedMessage";
    }
}
