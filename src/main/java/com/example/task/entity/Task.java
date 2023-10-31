package com.example.task.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
@Builder
public class Task {
     @Id
     @SequenceGenerator(
             name = "task_sequence",
             sequenceName = "task_sequence",
             allocationSize = 1
     )
     @GeneratedValue(
             strategy = GenerationType.SEQUENCE,
             generator = "task_sequence"
     )
    private Integer id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDate createdAt;
    private LocalDate completedAt;
    private Integer userid;

    public Task(String title,
                String description,
                Status status,
                LocalDate createdAt,
                LocalDate completedAt,
                Integer userid) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
        this.userid = userid;
    }
}
