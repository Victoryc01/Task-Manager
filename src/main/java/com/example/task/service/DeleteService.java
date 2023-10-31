package com.example.task.service;

import com.example.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteService {
    private TaskRepository taskRepository;
     @Autowired
    public DeleteService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void deleteTaskById(Integer taskId){
      boolean exists = taskRepository.existsById(taskId);
      if (exists){
          taskRepository.deleteById(taskId);
      }
    }
}
