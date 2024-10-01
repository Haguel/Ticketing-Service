package com.example.ticketing_service.dao;

import com.example.ticketing_service.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
