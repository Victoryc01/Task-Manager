package com.example.task.controller;

import com.example.task.service.DeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteController {
 private DeleteService deleteService;
      @Autowired
    public DeleteController(DeleteService deleteService) {
        this.deleteService = deleteService;
    }

    @PostMapping("/deleteTask/{taskId}")
    public String deleteTask(@PathVariable("taskId") Integer taskId){
          deleteService.deleteTaskById(taskId);
          return "taskDeletedMessage";

    }
}
