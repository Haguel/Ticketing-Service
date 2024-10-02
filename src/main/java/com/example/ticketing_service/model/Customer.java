package com.example.ticketing_service.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"tickets"})
@ToString(exclude = {"tickets"})
@Data
@Table(name = "customer")
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @OneToMany
    private Set<Ticket> tickets;

    public Customer(String name, String surname, String email, String phone) {
        this.name = name;
        this.surname = name;
        this.email = email;
        this.phone = phone;
    }
}
