package com.example.librarymanagement.service;

import com.example.librarymanagement.dto.request.BorrowCreateDTO;
import com.example.librarymanagement.entity.Book;
import com.example.librarymanagement.entity.Borrow;
import com.example.librarymanagement.entity.BorrowStatus;
import com.example.librarymanagement.entity.BorrowTicket;
import com.example.librarymanagement.exception.BookAlreadyReturnedException;
import com.example.librarymanagement.exception.ResourceNotFoundException;
import com.example.librarymanagement.repository.BookRepository;
import com.example.librarymanagement.repository.BorrowRepository;
import com.example.librarymanagement.repository.BorrowTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BorrowService {
    private final BorrowRepository borrowRepository;
    private final BorrowTicketRepository borrowTicketRepository;
    private final BookRepository bookRepository;

    public void create(BorrowCreateDTO borrowCreateDTO) {
        Borrow borrow = new Borrow();
        borrow.setUsername(borrowCreateDTO.getUsername());
        borrow.setBookId(borrowCreateDTO.getBookId());
        borrowRepository.save(borrow);
    }

    public void returnBook(Long ticketId) {
        BorrowTicket existingBorrowTicket = borrowTicketRepository.findById(ticketId).orElseThrow(
                () -> new ResourceNotFoundException("Borrow ticket with id = " + ticketId + " not found")
        );

        if (existingBorrowTicket.getBorrowStatus().equals(BorrowStatus.RETURNED)) {
            throw new BookAlreadyReturnedException("The book had already been returned");
        }

        existingBorrowTicket.setBorrowStatus(BorrowStatus.RETURNED);
        existingBorrowTicket.setReturnDate(LocalDateTime.now());

        Book book = bookRepository.findById(existingBorrowTicket.getBorrow().getBookId()).orElseThrow(
                () -> new ResourceNotFoundException
                        ("Book with id = " + existingBorrowTicket.getBorrow().getBookId() + " not found")
        );
        book.setStock(book.getStock() + 1);
        bookRepository.save(book);

        borrowTicketRepository.save(existingBorrowTicket);
    }
}
