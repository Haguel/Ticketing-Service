package com.example.ticketing_service.dao;

import com.example.ticketing_service.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query(value = "SELECT * FROM event e " +
            "WHERE event_date > :date " +
            "ORDER BY event_date" ,
            nativeQuery = true)
    List<Event> findNearestEventsByDate(@Param("date") LocalDateTime dateTime);
}
