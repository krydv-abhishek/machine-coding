package org.abhishek.parkinglot.strategy;

import org.abhishek.parkinglot.model.Ticket;

public interface PriceStrategy {

    double calculatePrice(Ticket ticket);
}
