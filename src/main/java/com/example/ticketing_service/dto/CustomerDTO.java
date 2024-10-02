package com.example.ticketing_service.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CustomerDTO {
    private String name;
    private String surname;
    private String email;
    private String phone;
}
