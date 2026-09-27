package com.example.librarymanagement.service;

import com.example.librarymanagement.dto.request.BorrowCreateDTO;
import com.example.librarymanagement.entity.Borrow;
import com.example.librarymanagement.repository.BorrowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BorrowService {
    private final BorrowRepository borrowRepository;

    public void create(BorrowCreateDTO borrowCreateDTO) {
        Borrow borrow = new Borrow();
        borrow.setUsername(borrowCreateDTO.getUsername());
        borrow.setBookId(borrowCreateDTO.getBookId());
        borrowRepository.save(borrow);
    }
}
