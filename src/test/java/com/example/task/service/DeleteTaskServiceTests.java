package com.example.task.service;

import com.example.task.entity.Status;
import com.example.task.entity.Task;
import com.example.task.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteTaskServiceTests {
    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private DeleteService deleteService;

    @Test
    public void checkIfDeleteServiceDeletesATaskFromTheDatabaseBYTheTaskId(){
        Task task = Task.builder()
                .title("Reading the NewYork Times NewsPaper")
                .description("i intend to finish every Topic about business development")
                .status(Status.PENDING)
                .createdAt(LocalDate.of(2000,4,15))
                .completedAt(LocalDate.now())
                .userid(2).build();
       when(taskRepository.existsById(1)).thenReturn(Boolean.TRUE);
       assertAll(()-> deleteService.deleteTaskById(1));
    }
}
