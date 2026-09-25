package com.example.librarymanagement.exception;

import com.example.librarymanagement.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(FileStorageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleFileStorageException(FileStorageException ex) {
        return new ErrorResponse("ERROR", ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ErrorResponse("ERROR", ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String msgError = "Validation error";
        if (!ex.getBindingResult().getFieldErrors().isEmpty()) {
            msgError = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        }

        return new ErrorResponse("ERROR", msgError, LocalDateTime.now());
    }
}
