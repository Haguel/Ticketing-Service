package com.example.ticketing_service.dao;

import com.example.ticketing_service.model.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketStatusRepository extends JpaRepository<TicketStatus, Long> {
}
