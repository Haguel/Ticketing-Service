package com.example.ticketing_service.service;

import com.example.ticketing_service.dao.TicketRepository;
import com.example.ticketing_service.dao.TicketStatusRepository;
import com.example.ticketing_service.model.Customer;
import com.example.ticketing_service.model.Event;
import com.example.ticketing_service.model.Ticket;
import com.example.ticketing_service.model.TicketStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketStatusRepository ticketStatusRepository;

    public Ticket createTicket(double cost, int number,  Event event) {
        TicketStatus notSoldStatus = ticketStatusRepository.findUnsoldStatus();
        Ticket ticket = new Ticket(cost, number, event, notSoldStatus);

        ticketRepository.save(ticket);

        return ticket;
    }

    public List<Ticket> findUnsoldTickets(Long eventId) {
        return ticketRepository.findUnsoldTickets(eventId);
    }

    public void assignTicketTo(Ticket ticket, Customer customer) {
        TicketStatus soldStatus = ticketStatusRepository.findSoldStatus();

        ticket.setTicketStatus(soldStatus);
        ticket.setCustomer(customer);

        ticketRepository.save(ticket);
    }
}
