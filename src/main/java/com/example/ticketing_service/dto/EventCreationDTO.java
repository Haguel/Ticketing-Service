package com.example.ticketing_service.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class EventCreationDTO {
    private EventDTO eventDTO;
    private List<TicketPackDTO> tickets;
    private PlaceDTO placeDTO;
}
