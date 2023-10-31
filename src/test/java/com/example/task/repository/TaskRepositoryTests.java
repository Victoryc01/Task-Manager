package com.example.task.repository;

import com.example.task.entity.Status;
import com.example.task.entity.Task;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TaskRepositoryTests {
    @Autowired
    private TaskRepository taskRepository;
@Test
public void checkIfTaskRepositoryListsAllTasksInTheDatabase(){
    Task task = Task.builder()
            .title("Reading the NewYork Times NewsPaper")
            .description("i intend to finish every Topic about business development")
            .status(Status.PENDING)
            .createdAt(LocalDate.of(2000,4,15))
            .completedAt(LocalDate.now())
            .userid(2).build();

    Task task1 = Task.builder()
            .title("Writing my journal")
            .description("i intend to finish every Topic about business development in my Journal")
            .status(Status.PENDING)
            .createdAt(LocalDate.of(2000,4,15))
            .completedAt(LocalDate.now())
            .userid(2).build();
    taskRepository.save(task);
    taskRepository.save(task1);

    List<Task> taskList = taskRepository.findAll();
    Assertions.assertThat(taskList).isNotNull();
    Assertions.assertThat(taskList.size()).isGreaterThan(1);


}

@Test
public void checkIfTaskRepositoryCanFindATaskByAGivenID(){
    Task task = Task.builder()
            .title("Reading the NewYork Times NewsPaper")
            .description("i intend to finish every Topic about business development")
            .status(Status.PENDING)
            .createdAt(LocalDate.of(2000,4,15))
            .completedAt(LocalDate.now())
            .userid(2).build();
   Task savedTask = taskRepository.save(task);
    Optional<Task> taskWithId = taskRepository.findTaskById(savedTask.getId());
    Assertions.assertThat(savedTask).isNotNull();
    Assertions.assertThat(taskWithId).isPresent();
}
@Test
public void checkIfTaskRepositoryCanFindATaskByAGivenStatus(){
    Task task = Task.builder()
            .title("Reading the NewYork Times NewsPaper")
            .description("i intend to finish every Topic about business development")
            .status(Status.PENDING)
            .createdAt(LocalDate.of(2000,4,15))
            .completedAt(LocalDate.now())
            .userid(2).build();

    Task task1 = Task.builder()
            .title("Writing my journal")
            .description("i intend to finish every Topic about business development in my Journal")
            .status(Status.IN_PROGRESS)
            .createdAt(LocalDate.of(2000,4,15))
            .completedAt(LocalDate.now())
            .userid(2).build();
    taskRepository.save(task);
    taskRepository.save(task1);
   List<Task> taskListByAGivenStatus = taskRepository.findByStatus(Status.PENDING);
    List<Task> taskListByAGivenStatus1 = taskRepository.findByStatus(Status.IN_PROGRESS);

   Assertions.assertThat(taskListByAGivenStatus.size()).isEqualTo(1);
    Assertions.assertThat(taskListByAGivenStatus1.size()).isEqualTo(1);

}
@Test
public void checkIfTaskRepositoryCanListAllDistinctStatusFoundInADatabase(){
    Task task = Task.builder()
            .title("Reading the NewYork Times NewsPaper")
            .description("i intend to finish every Topic about business development")
            .status(Status.PENDING)
            .createdAt(LocalDate.of(2000,4,15))
            .completedAt(LocalDate.now())
            .userid(2).build();

    Task task1 = Task.builder()
            .title("Writing my journal")
            .description("i intend to finish every Topic about business development in my Journal")
            .status(Status.IN_PROGRESS)
            .createdAt(LocalDate.of(2000,4,15))
            .completedAt(LocalDate.now())
            .userid(2).build();
    taskRepository.save(task);
    taskRepository.save(task1);
    List<Status> allStatusesInTheDatabase = taskRepository.findDistinctByStatus();
    Assertions.assertThat(allStatusesInTheDatabase).containsOnly(Status.IN_PROGRESS,Status.PENDING);
}
}
