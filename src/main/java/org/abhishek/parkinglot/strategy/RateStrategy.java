package org.abhishek.parkinglot.strategy;

public interface RateStrategy {

    double calculateRate(long durationInHours);
}
