package com.example.ticketing_service.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"customer", "event", "ticketStatus"})
@ToString(exclude = {"customer", "event", "ticketStatus"})
@Data
@Table(name = "ticket")
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private double cost;

    @Column
    private int number;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @OneToOne
    @JoinColumn(name = "ticket_status_id")
    private TicketStatus ticketStatus;
}
