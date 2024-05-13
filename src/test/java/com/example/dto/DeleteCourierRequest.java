package com.example.dto;

public class DeleteCourierRequest {

    private final Long id;

    public DeleteCourierRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
