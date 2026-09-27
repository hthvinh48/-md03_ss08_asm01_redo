package com.example.librarymanagement.controller;

import com.example.librarymanagement.dto.request.ReaderCreateDTO;
import com.example.librarymanagement.dto.response.ApiResponse;
import com.example.librarymanagement.entity.Reader;
import com.example.librarymanagement.service.ReaderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/readers")
@RequiredArgsConstructor
public class ReaderController {
    private final ReaderService readerService;

    @PostMapping
    public ResponseEntity<ApiResponse<Reader>> createReader(
            @Valid @ModelAttribute ReaderCreateDTO readerCreateDTO
    ) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                "SUCCESS", "Reader created successfully", readerService.createReader(readerCreateDTO)
        ));
    }
}
