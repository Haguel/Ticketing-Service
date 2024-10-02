package com.example.ticketing_service.dao;

import com.example.ticketing_service.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query(value = "SELECT * FROM ticket t " +
            "JOIN ticket_status ts ON t.ticket_status_id = ts.id " +
            "WHERE t.event_id = :eventId " +
            "AND ts.status = 'unsold' ",
            nativeQuery = true)
    List<Ticket> findUnsoldTickets(@Param("eventId") Long eventId);
}
