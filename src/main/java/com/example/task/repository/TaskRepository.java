package com.example.task.repository;

import com.example.task.entity.Status;
import com.example.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    List<Task> findAllByUserid(Integer userid);
    Optional<Task> findTaskById (Integer userid);
    @Query("SELECT DISTINCT t.status FROM Task t")
    List<Status> findDistinctByStatus();
    List<Task> findByStatus(Status status);
}
