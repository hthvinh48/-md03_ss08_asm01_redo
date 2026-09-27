package com.example.librarymanagement.validator;

import com.example.librarymanagement.annotation.ExistingBookId;
import com.example.librarymanagement.repository.BookRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookIdValidator implements ConstraintValidator<ExistingBookId, Long> {
    private final BookRepository bookRepository;

    @Override
    public boolean isValid(Long bookId, ConstraintValidatorContext context) {
        if (bookId == null) {
            return true;
        }

        return bookRepository.existsById(bookId);
    }
}
