package com.example.task.service;

import com.example.task.entity.Status;
import com.example.task.entity.Task;
import com.example.task.repository.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EditTaskServiceTests {
    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private EditTaskService editTaskService;


    @Test
    public void checkIfEditTaskServiceEditsATask(){
        Task task = Task.builder()
                .title("Reading the NewYork Times NewsPaper")
                .description("i intend to finish every Topic about business development")
                .status(Status.PENDING)
                .createdAt(LocalDate.of(2000,4,15))
                .completedAt(LocalDate.now())
                .userid(2).build();
      when(taskRepository.findTaskById(1)).thenReturn(Optional.ofNullable(task));
      Task editedTask = editTaskService.updateTask(1,"assignments","finishing my assignments");
        Assertions.assertThat(editedTask).isNotNull();
    }
}
