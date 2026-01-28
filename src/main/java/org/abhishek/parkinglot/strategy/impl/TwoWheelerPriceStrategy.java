package org.abhishek.parkinglot.strategy.impl;

import org.abhishek.parkinglot.model.Ticket;
import org.abhishek.parkinglot.strategy.PriceStrategy;
import org.abhishek.parkinglot.strategy.RateStrategy;

import java.time.Duration;

public class TwoWheelerPriceStrategy implements PriceStrategy {

    private final RateStrategy rateStrategy;

    public TwoWheelerPriceStrategy(RateStrategy rateStrategy) {
        this.rateStrategy = rateStrategy;
    }

    @Override
    public double calculatePrice(Ticket ticket) {
        long durationInHours = Duration.between(ticket.getExitAt(), ticket.getEntryAt()).toHours();
        return rateStrategy.calculateRate(Math.max(1, durationInHours));
    }
}
