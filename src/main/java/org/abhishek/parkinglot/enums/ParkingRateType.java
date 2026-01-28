package org.abhishek.parkinglot.enums;

public enum ParkingRateType {
    WEEKEND(1);

    private int value;

    ParkingRateType(int value) {
        this.value = value;
    }

    public int getValue(ParkingRateType type) {
        return this.value;
    }
}
