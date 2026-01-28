package org.abhishek.parkinglot.repository;

import org.abhishek.parkinglot.exception.InvalidTicketException;
import org.abhishek.parkinglot.model.Ticket;

import java.util.concurrent.ConcurrentHashMap;

public class TicketRepository {

    private final ConcurrentHashMap<String, Ticket> ticketMap = new ConcurrentHashMap<>();

    public void saveTicket(Ticket ticket) {
        ticketMap.put(ticket.getId(), ticket);
    }

    public Ticket getTicket(String id) {
        if (!ticketMap.containsKey(id)) {
            throw new InvalidTicketException("Ticket not found");
        }
        return ticketMap.get(id);
    }
}
