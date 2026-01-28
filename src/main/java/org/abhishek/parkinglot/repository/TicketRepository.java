package org.abhishek.parkinglot.repository;

import org.abhishek.parkinglot.exception.InvalidTicketException;
import org.abhishek.parkinglot.model.Ticket;

import java.util.HashMap;
import java.util.Map;

public class TicketRepository {

    private final Map<String, Ticket> ticketMap = new HashMap<>();

    public void saveTicket(Ticket ticket) {
        ticketMap.put(ticket.getId(), ticket);
    }

    public Ticket getTicket(String id) {
        if(!ticketMap.containsKey(id)) {
            throw new InvalidTicketException("Ticket not found");
        }
        return ticketMap.get(id);
    }
}
