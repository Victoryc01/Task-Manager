package com.example.task.controller;

;
import com.example.task.entity.Status;
import com.example.task.entity.Task;
import com.example.task.service.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class TaskController {
    private TaskService taskService;
     @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }



    @GetMapping("/creatingTask")
    public String getTaskPage(Model model){
      model.addAttribute("task", new Task());
      return "taskPage";
    }



     @PostMapping("/creatingTask")
    public String createTask(@ModelAttribute Task task, HttpSession session){
         Integer userid = (Integer) session.getAttribute("userId");
         Task createdTask = taskService.addTask(task.getTitle(),
                 task.getDescription(),
                 Status.valueOf(task.getStatus().getValue()),
                 task.getCreatedAt(), task.getCompletedAt(),userid);
          return createdTask == null ? "errorPage" : "taskCreationSuccessful";
    }
}
