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
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ViewTaskServiceTests {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private ViewTaskService viewTaskService;

    @Test
    public void checkIfViewTaskServiceListsAllTaskInTheDatabaseByTheUsersId(){
        Task task = Task.builder()
                .title("Reading the NewYork Times NewsPaper")
                .description("i intend to finish every Topic about business development")
                .status(Status.PENDING)
                .createdAt(LocalDate.of(2000,4,15))
                .completedAt(LocalDate.now())
                .userid(2).build();

        when(taskRepository.findAllByUserid(2)).thenReturn(List.of(task));
        List<Task> taskList = viewTaskService.listAllTaskByUsersId(2);
        Assertions.assertThat(taskList).isNotNull();
        Assertions.assertThat(taskList.size()).isEqualTo(1);
    }
}
