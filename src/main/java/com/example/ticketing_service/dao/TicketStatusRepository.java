package com.example.ticketing_service.dao;

import com.example.ticketing_service.model.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketStatusRepository extends JpaRepository<TicketStatus, Long> {
    @Query("SELECT ts FROM TicketStatus ts WHERE ts.status = 'unsold'")
    TicketStatus findUnsoldStatus();

    @Query("SELECT ts FROM TicketStatus ts WHERE ts.status = 'sold'")
    TicketStatus findSoldStatus();
}
