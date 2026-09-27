package com.example.librarymanagement.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReaderCreateDTO {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String fullName;

    @Pattern(
            regexp = "^(0|\\+84)([35789])[0-9]{8}$",
            message = "Invalid Vietnamese phone number"
    )
    private String phoneNumber;

    @NotBlank
    private String address;

    private MultipartFile avatarFile;
}
