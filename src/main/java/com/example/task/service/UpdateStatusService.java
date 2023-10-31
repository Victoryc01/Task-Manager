package com.example.task.service;

import com.example.task.entity.Status;
import com.example.task.entity.Task;
import com.example.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class UpdateStatusService {
    private TaskRepository taskRepository;
      @Autowired
    public UpdateStatusService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

     @Transactional
    public Task updateStatus(Integer taskId, Status status){
       Task task = taskRepository.findTaskById(taskId)
               .orElseThrow(()-> new IllegalStateException("Task with id: " + taskId +" does not exists"));
       if (status != null && !status.getValue().isEmpty() && !Objects.equals(task.getStatus(),status)){
           task.setStatus(status);
       }
       return task;
    }
}
