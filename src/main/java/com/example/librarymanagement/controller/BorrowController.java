package com.example.librarymanagement.controller;

import com.example.librarymanagement.dto.request.BorrowCreateDTO;
import com.example.librarymanagement.dto.response.ApiResponse;
import com.example.librarymanagement.service.BorrowService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/borrows")
@RequiredArgsConstructor
public class BorrowController {
    private final BorrowService borrowService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(
            @Valid @RequestBody BorrowCreateDTO borrowCreateDTO
    ) {
        borrowService.create(borrowCreateDTO);
        return ResponseEntity.ok(new ApiResponse<>(
                "SUCCESS", "Book borrowed successfully", null
        ));
    }
}
