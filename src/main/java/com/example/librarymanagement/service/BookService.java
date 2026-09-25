package com.example.librarymanagement.service;

import com.example.librarymanagement.dto.request.BookCreateDTO;
import com.example.librarymanagement.dto.request.BookUpdateStockDTO;
import com.example.librarymanagement.entity.Book;
import com.example.librarymanagement.exception.FileStorageException;
import com.example.librarymanagement.exception.ResourceNotFoundException;
import com.example.librarymanagement.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final String UPLOAD_DIRECTORY = "uploads";

    public Book createBook(BookCreateDTO bookCreateDTO) {
        MultipartFile file = bookCreateDTO.getCoverImage();

        if (file == null || file.isEmpty()) {
            throw new FileStorageException("File is empty");
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIRECTORY);

        try {
            Files.createDirectories(path);
            Path filePath = path.resolve(fileName);
            file.transferTo(filePath);
        } catch (IOException e) {
            throw new FileStorageException("File could not be saved", e);
        }

        Book book = new Book();
        book.setTitle(bookCreateDTO.getTitle());
        book.setAuthor(bookCreateDTO.getAuthor());
        book.setStock(bookCreateDTO.getStock());
        book.setCoverUrl(fileName);
        return bookRepository.save(book);
    }

    public void updateBook(Long id, BookUpdateStockDTO bookUpdateStockDTO) {
        Book existingBook = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book with id " + id + " not found")
        );

        existingBook.setStock(bookUpdateStockDTO.getStock());
        bookRepository.save(existingBook);
    }
}
