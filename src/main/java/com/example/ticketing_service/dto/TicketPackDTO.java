package com.example.ticketing_service.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TicketPackDTO {
    private int count;
    private double cost;
}
