package com.example.task.service;

import com.example.task.entity.Task;
import com.example.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class EditTaskService {
private TaskRepository taskRepository;
     @Autowired
    public EditTaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @Transactional
    public Task updateTask(Integer taskId, String title, String description){
        Task task = taskRepository.findTaskById(taskId)
                .orElseThrow(()-> new IllegalStateException("Task with id: " + taskId +" does not exists"));
        if (title != null && !title.isEmpty() && !Objects.equals(task.getTitle(),title)){
            task.setTitle(title);
        }
        if (description != null && !description.isEmpty() && !Objects.equals(task.getDescription(),description)){
            task.setDescription(description);
        }
        return task;
    }
}
