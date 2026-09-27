package com.example.librarymanagement.repository;

import com.example.librarymanagement.entity.BorrowTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowTicketRepository extends JpaRepository<BorrowTicket, Long> {
}
