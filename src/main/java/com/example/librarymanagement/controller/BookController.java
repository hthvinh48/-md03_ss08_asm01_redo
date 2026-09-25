package com.example.librarymanagement.controller;

import com.example.librarymanagement.dto.request.BookCreateDTO;
import com.example.librarymanagement.dto.request.BookUpdateStockDTO;
import com.example.librarymanagement.dto.response.ApiResponse;
import com.example.librarymanagement.entity.Book;
import com.example.librarymanagement.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ApiResponse<Book>> create(
            @Valid @ModelAttribute BookCreateDTO bookCreateDTO
    ) {
        return ResponseEntity.ok(new ApiResponse<>(
                "SUCCESS", "created new book", bookService.createBook(bookCreateDTO)
        ));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Void>> update(
            @PathVariable Long id,
            @Valid @RequestBody BookUpdateStockDTO bookUpdateStockDTO
    ) {
        bookService.updateBook(id, bookUpdateStockDTO);
        return ResponseEntity.ok(new ApiResponse<>(
                "SUCCESS", "Book stock updated successfully", null
        ));
    }
}
