package com.example.task.controller;

import com.example.task.entity.Task;
import com.example.task.repository.TaskRepository;
import com.example.task.service.UpdateStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UpdateStatusController {
    private UpdateStatusService updateStatusService;
    private TaskRepository taskRepository;
      @Autowired
    public UpdateStatusController(UpdateStatusService updateStatusService,TaskRepository taskRepository) {
        this.updateStatusService = updateStatusService;
        this.taskRepository = taskRepository;
    }

    @GetMapping("/updateStatus/{taskId}")
    public String getUpdateStatusPage(@PathVariable("taskId") Integer taskId, Model model){
          Task existingTask = taskRepository.findTaskById(taskId).orElse(null);
          if (existingTask == null){
              return "errorPage";
          }
          model.addAttribute("updateStatus", existingTask);
          return "updateStatus";
    }


    @PostMapping("/updateStatus/{taskId}")
    public String updateStatus(@PathVariable("taskId") Integer taskId, @ModelAttribute Task task){
       Task updatedStatus = updateStatusService.updateStatus(taskId,task.getStatus());
       return updatedStatus == null ? "errorPage" : "taskUpdatedMessage";
    }
}
