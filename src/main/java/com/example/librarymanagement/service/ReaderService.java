package com.example.librarymanagement.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.librarymanagement.dto.request.ReaderCreateDTO;
import com.example.librarymanagement.entity.Reader;
import com.example.librarymanagement.exception.FileStorageException;
import com.example.librarymanagement.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;
    private final Cloudinary cloudinary;

    public Reader createReader(ReaderCreateDTO readerCreateDTO) throws IOException {
        if (readerRepository.existsByEmail(readerCreateDTO.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        MultipartFile file = readerCreateDTO.getAvatarFile();

        if (file == null || file.isEmpty()) {
            throw new FileStorageException("Avatar file is required");
        }

        String contentType = file.getContentType();

        if (!"image/png".equals(contentType)
                && !"image/jpeg".equals(contentType)) {
            throw new FileStorageException("Only PNG, JPG and JPEG files are allowed");
        }

        Reader reader = new Reader();
        reader.setFullName(readerCreateDTO.getFullName());
        reader.setEmail(readerCreateDTO.getEmail());
        reader.setAddress(readerCreateDTO.getAddress());
        reader.setPhoneNumber(readerCreateDTO.getPhoneNumber());

        Map<?, ?> result = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.emptyMap()
        );

        String avatarUrl = (String) result.get("url");
        reader.setAvatar(avatarUrl);
        return readerRepository.save(reader);
    }
}
