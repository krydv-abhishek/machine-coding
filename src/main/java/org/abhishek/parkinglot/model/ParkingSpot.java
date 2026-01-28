package org.abhishek.parkinglot.model;

import org.abhishek.parkinglot.enums.ParkingSpotStatus;
import org.abhishek.parkinglot.enums.ParkingSpotType;

import java.util.UUID;

public class ParkingSpot {

    private String id;
    private String name;
    private ParkingLot parkingLot;
    private ParkingFloor parkingFloor;
    private ParkingSpotStatus status;
    private ParkingSpotType spotType;


    public ParkingSpot(String name, ParkingSpotType spotType) {
        this.id = UUID.randomUUID().toString();
        this.status = ParkingSpotStatus.AVAILABLE;
        this.name = name;
        this.spotType = spotType;
    }

    public String getId() {
        return id;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingFloor getParkingFloor() {
        return parkingFloor;
    }

    public void setParkingFloor(ParkingFloor parkingFloor) {
        this.parkingFloor = parkingFloor;
    }

    public ParkingSpotStatus getStatus() {
        return status;
    }

    public void occupy() {
        this.status = ParkingSpotStatus.OCCUPIED;
    }

    public boolean isAvailable() {
        return ParkingSpotStatus.AVAILABLE.equals(this.status);
    }

    public void release() {
        this.status = ParkingSpotStatus.AVAILABLE;
    }

    public ParkingSpotType getSpotType() {
        return spotType;
    }

    public void setSpotType(ParkingSpotType spotType) {
        this.spotType = spotType;
    }
}
