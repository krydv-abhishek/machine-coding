package org.abhishek.parkinglot.model;

import java.util.List;
import java.util.UUID;

public class ParkingLot {
    private String id;
    private List<ParkingFloor>  parkingFloors;

    public ParkingLot(List<ParkingFloor> parkingFloors) {
        this.id = UUID.randomUUID().toString();
        this.parkingFloors = parkingFloors;
    }

    public List<ParkingFloor> getParkingFloors() {
        return parkingFloors;
    }

    public void setParkingFloors(List<ParkingFloor> parkingFloors) {
        this.parkingFloors = parkingFloors;
    }
    public String getId() {
        return id;
    }

}
