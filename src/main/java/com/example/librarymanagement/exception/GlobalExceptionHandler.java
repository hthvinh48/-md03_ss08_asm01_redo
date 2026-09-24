package com.example.librarymanagement.exception;

import com.example.librarymanagement.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(FileStorageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleFileStorageException(FileStorageException ex) {
        return new ApiResponse<>("ERROR", ex.getMessage(), null);
    }
}
