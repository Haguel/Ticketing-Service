package com.example.ticketing_service.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PlaceDTO {
    private String address;
    private String name;
}
