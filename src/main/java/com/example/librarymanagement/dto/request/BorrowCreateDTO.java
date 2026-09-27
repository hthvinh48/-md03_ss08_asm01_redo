package com.example.librarymanagement.dto.request;

import com.example.librarymanagement.annotation.ExistingBookId;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BorrowCreateDTO {
    @NotBlank(message = "username is required")
    private String username;

    @ExistingBookId
    private Long bookId;
}
