package org.arquitetura.dto;


import lombok.Data;

import java.time.Instant;

@Data
public class ApiResponse<T> {
    private final Instant timestamp = Instant.now();
    private int status;
    private String error;
    private T data;

    public ApiResponse(int status, String error, T data) {
        this.status = status;
        this.error = error;
        this.data = data;
    }
}
