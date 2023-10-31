package com.example.task.controller;

import com.example.task.entity.Status;
import com.example.task.entity.Task;
import com.example.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StatusController {
    private TaskRepository taskRepository;
      @Autowired
    public StatusController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @GetMapping("/statuses")
    public String viewAllStatuses(Model model){
        List<Status> statuses = taskRepository.findDistinctByStatus();
        model.addAttribute("statuses",statuses);
            return "viewAllStatus";
  }


  @GetMapping("/status/{statusName}")
  public String listTaskByStatus(@PathVariable Status statusName, Model model){
    List<Task> task = taskRepository.findByStatus(statusName);
    model.addAttribute("statusName",statusName);
    model.addAttribute("task",task);
    return "viewTaskByStatus";
  }
}
