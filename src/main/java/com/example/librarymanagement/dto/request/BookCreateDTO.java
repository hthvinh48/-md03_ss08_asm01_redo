package com.example.librarymanagement.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookCreateDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotNull
    @Min(value = 1)
    private Integer stock;

    private MultipartFile coverImage;
}
