package com.example.ticketing_service.service;

import com.example.ticketing_service.dto.*;
import com.example.ticketing_service.model.Customer;
import com.example.ticketing_service.model.Event;
import com.example.ticketing_service.model.Place;
import com.example.ticketing_service.model.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
@Service
public class Menu {
    private final EventService eventService;
    private final PlaceService placeService;
    private final TicketService ticketService;
    private final CustomerService customerService;

    private Event createEvent(EventCreationDTO eventCreationDTO) {
        Place place = placeService.createPlace(eventCreationDTO.getPlaceDTO());
        Event event = eventService.createEvent(eventCreationDTO.getEventDTO(), place);

        int numberCounter = 1;
        for(TicketPackDTO ticketPackDTO : eventCreationDTO.getTickets()) {
            for(int i = 0; i < ticketPackDTO.getCount(); i++) {
                ticketService.createTicket(ticketPackDTO.getCost(), numberCounter++, event);
            }
        }

        return event;
    }

    private void createCustomerFromConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter customer first name:");
        String firstName = scanner.nextLine();

        System.out.println("Enter customer last name:");
        String lastName = scanner.nextLine();

        System.out.println("Enter customer email:");
        String email = scanner.nextLine();

        System.out.println("Enter customer phone number:");
        String phoneNumber = scanner.nextLine();

        CustomerDTO customerDTO = new CustomerDTO(firstName, lastName, email, phoneNumber);

        Customer customer = customerService.createCustomer(customerDTO);
        System.out.println("Customer created successfully!");
        System.out.println(customer);
    }

    private void createEventFromConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter event name:");
        String eventName = scanner.nextLine();

        System.out.println("Enter event date (yyyy-MM-ddTHH:mm):");
        String eventDateStr = scanner.nextLine();
        LocalDateTime eventDate = LocalDateTime.parse(eventDateStr);

        System.out.println("Enter place name:");
        String placeName = scanner.nextLine();

        System.out.println("Enter place address:");
        String placeAddress = scanner.nextLine();

        System.out.println("Enter number of ticket packs:");
        int ticketPackCount = scanner.nextInt();
        scanner.nextLine(); // consume newline

        List<TicketPackDTO> ticketPacks = new ArrayList<>();
        for (int i = 0; i < ticketPackCount; i++) {
            System.out.println("Enter cost for ticket pack " + (i + 1) + ":");
            double cost = scanner.nextDouble();

            System.out.println("Enter count for ticket pack " + (i + 1) + ":");
            int count = scanner.nextInt();
            scanner.nextLine();

            TicketPackDTO ticketPackDTO = new TicketPackDTO(count, cost);
            ticketPacks.add(ticketPackDTO);
        }

        EventCreationDTO eventCreationDTO = new EventCreationDTO(
                new EventDTO(eventDate, eventName),
                ticketPacks,
                new PlaceDTO(placeAddress, placeName)
        );

        Event event = createEvent(eventCreationDTO);
        System.out.println("Event created successfully!");
        System.out.println(event);
    }

    private void buyUnsoldTicketsForEvent() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter event ID:");
        Long eventId = scanner.nextLong();
        scanner.nextLine();

        List<Ticket> unsoldTickets = ticketService.findUnsoldTickets(eventId);
        if (unsoldTickets.isEmpty()) {
            System.out.println("No available tickets for this event.");
            return;
        }

        System.out.println("Enter customer ID:");
        Long customerId = scanner.nextLong();
        scanner.nextLine();

        Customer customer = customerService.findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.println("Available ticket packs:");
        for (int i = 0; i < unsoldTickets.size(); i++) {
            Ticket ticket = unsoldTickets.get(i);
            System.out.println((i + 1) + ". Ticket ID: " + ticket.getId() + ", Cost: " + ticket.getCost());
        }

        System.out.println("Choose a ticket pack to buy from:");
        int ticketChoice = scanner.nextInt();
        scanner.nextLine();

        if (ticketChoice < 1 || ticketChoice > unsoldTickets.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Ticket chosenTicket = unsoldTickets.get(ticketChoice - 1);
        ticketService.assignTicketTo(chosenTicket, customer);

        System.out.println("Ticket bought successfully!");
    }

    private void findNearestEventsByDate() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the current date and time (yyyy-MM-ddTHH:mm):");
        String currentDateStr = scanner.nextLine();
        LocalDateTime currentDate = LocalDateTime.parse(currentDateStr);

        List<Event> nearestEvents = eventService.findNearestEventsByDate(currentDate);
        if (nearestEvents.isEmpty()) {
            System.out.println("No upcoming events found.");
            return;
        }

        System.out.println("Upcoming events:");
        for (Event event : nearestEvents) {
            System.out.println(event.toString());
        }
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Console Menu:");
            System.out.println("1. Create Event");
            System.out.println("2. Create Customer");
            System.out.println("3. Buy unsold tickets for Event");
            System.out.println("4. Find Nearest Events by Date");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createEventFromConsole();
                    break;
                case 2:
                    createCustomerFromConsole();
                    break;
                case 3:
                    buyUnsoldTicketsForEvent();
                    break;
                case 4:
                    findNearestEventsByDate();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
