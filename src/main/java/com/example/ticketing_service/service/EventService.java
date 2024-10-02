package com.example.ticketing_service.service;

import com.example.ticketing_service.dao.EventRepository;
import com.example.ticketing_service.dto.EventDTO;
import com.example.ticketing_service.model.Event;
import com.example.ticketing_service.model.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class EventService {
    private final EventRepository eventRepository;

    public Event createEvent(EventDTO eventDTO, Place place) {
        Event event = new Event(eventDTO.getEventDate(), eventDTO.getName(), place);

        eventRepository.save(event);

        return event;
    }

    public List<Event> findNearestEventsByDate(LocalDateTime dateTime) {
        return eventRepository.findNearestEventsByDate(dateTime);
    }
}
