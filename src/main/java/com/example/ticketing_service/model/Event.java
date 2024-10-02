package com.example.ticketing_service.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"tickets", "place"})
@ToString(exclude = {"tickets", "place"})
@Data
@Table(name = "event")
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime eventDate;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    @OneToMany
    private Set<Ticket> tickets;

    public Event(LocalDateTime eventDate, String name, Place place) {
        this.eventDate = eventDate;
        this.name = name;
        this.place = place;
    }
}
