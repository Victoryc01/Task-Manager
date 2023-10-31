package com.example.task.service;

import com.example.task.entity.Status;
import com.example.task.entity.Task;
import com.example.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.InputMismatchException;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task addTask (String title,
                         String description,
                         Status status,
                         LocalDate createdAt,
                         LocalDate completedAt,
                         Integer userid){

        if (title == null || createdAt.isAfter(completedAt)){
            throw new InputMismatchException("invalid input");
        } else {

            Task task = new Task();
            task.setTitle(title);
            task.setDescription(description);
            task.setStatus(status);
            task.setCreatedAt(createdAt);
            task.setCompletedAt(completedAt);
            task.setUserid(userid);
            taskRepository.save(task);
            return task;
        }
    }
}
