package com.example.task.service;

import com.example.task.entity.Task;
import com.example.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewTaskService {
    private TaskRepository taskRepository;
     @Autowired
    public ViewTaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> listAllTaskByUsersId(Integer userid){
         return taskRepository.findAllByUserid(userid);
    }
}
