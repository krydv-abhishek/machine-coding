package org.abhishek.parkinglot.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ParkingFloor {
    private String id;
    private String name;
    private List<ParkingSpot> parkingSpots;

    public ParkingFloor(String name, List<ParkingSpot> parkingSpots) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.parkingSpots = parkingSpots;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public void setParkingSpots(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public void addParkingSpot(ParkingSpot parkingSpot) {
        if (this.parkingSpots != null && this.parkingSpots.isEmpty()) {
            parkingSpots.add(parkingSpot);
        } else {
            this.parkingSpots = new ArrayList<>();
            this.parkingSpots.add(parkingSpot);
        }
    }
}
