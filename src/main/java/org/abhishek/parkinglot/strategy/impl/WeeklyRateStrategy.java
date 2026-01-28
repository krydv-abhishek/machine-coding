package org.abhishek.parkinglot.strategy.impl;

import org.abhishek.parkinglot.strategy.RateStrategy;

public class WeeklyRateStrategy implements RateStrategy {

    private final double weeklyRate;

    public WeeklyRateStrategy(double hourlyRate) {
        this.weeklyRate = hourlyRate;
    }

    @Override
    public double calculateRate(long durationInHours) {
        return this.weeklyRate * durationInHours/24;
    }
}
