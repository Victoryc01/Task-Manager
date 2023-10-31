package com.example.task.service;

import com.example.task.entity.Status;
import com.example.task.entity.Task;
import com.example.task.repository.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTests {
    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private TaskService taskService;

    @Test
    public void checkIfTaskServiceSavesATaskToTheDatabase(){
        Task task = Task.builder()
                .title("Reading the NewYork Times NewsPaper")
                .description("i intend to finish every Topic about business development")
                .status(Status.PENDING)
                .createdAt(LocalDate.of(2000,4,15))
                .completedAt(LocalDate.now())
                .userid(2).build();

        when(taskRepository.save(Mockito.any(Task.class))).thenReturn(task);
        Task addedTask = taskService.addTask(
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreatedAt(),
                task.getCompletedAt(),
                task.getUserid()
        );
        Assertions.assertThat(addedTask).isNotNull();

    }
}
