package com.example.task.controller;

import com.example.task.entity.Task;
import com.example.task.service.ViewTaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ViewTaskController {
    private ViewTaskService viewTaskService;
     @Autowired
    public ViewTaskController(ViewTaskService viewTaskService) {
        this.viewTaskService = viewTaskService;
    }

    @GetMapping("/viewTask")
    public String listAllTaskCreatedByTheUser(HttpSession session, Model model){
         Integer userId = (Integer) session.getAttribute("userId");
        List<Task> usersTask = viewTaskService.listAllTaskByUsersId(userId);
        model.addAttribute("taskList",usersTask);
         return "viewTask";
    }
}
