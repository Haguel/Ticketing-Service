package com.example.ticketing_service.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class EventDTO {
    private LocalDateTime eventDate;
    private String name;
}
