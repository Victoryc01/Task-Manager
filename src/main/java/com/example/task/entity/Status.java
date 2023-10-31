package com.example.task.entity;

import lombok.Getter;

@Getter
public enum Status {
    IN_PROGRESS("IN_PROGRESS"),
    PENDING("PENDING"),
    DONE("DONE");
    private String value;

    Status(String value) {
        this.value = value;
    }

}
