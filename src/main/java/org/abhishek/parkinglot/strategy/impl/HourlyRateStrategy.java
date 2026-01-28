package org.abhishek.parkinglot.strategy.impl;

import org.abhishek.parkinglot.strategy.RateStrategy;

public class HourlyRateStrategy implements RateStrategy {

    private final double hourlyRate;

    public HourlyRateStrategy(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateRate(long durationInHours) {
        return this.hourlyRate * durationInHours;
    }
}
