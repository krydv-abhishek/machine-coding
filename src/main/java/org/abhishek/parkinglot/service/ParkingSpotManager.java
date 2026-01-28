package org.abhishek.parkinglot.service;

import org.abhishek.parkinglot.enums.ParkingSpotType;
import org.abhishek.parkinglot.enums.VehicleType;
import org.abhishek.parkinglot.exception.NoSpotAvailableException;
import org.abhishek.parkinglot.model.ParkingFloor;
import org.abhishek.parkinglot.model.ParkingLot;
import org.abhishek.parkinglot.model.ParkingSpot;
import org.abhishek.parkinglot.repository.ParkingLotRepository;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingSpotManager {

    private final ParkingLotRepository parkingLotRepository;
    private final ReentrantLock lock = new ReentrantLock(true); // fair lock


    public ParkingSpotManager(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    public ParkingSpot findAndReserveSpot(VehicleType vehicleType) {
        lock.lock();
        try {
            for (ParkingLot parkingLot : parkingLotRepository.getAllParkingLot()) {
                List<ParkingFloor> parkingFloors = parkingLot.getParkingFloors();
                for (ParkingFloor parkingFloor : parkingFloors) {
                    List<ParkingSpot> parkingSpots = parkingFloor.getParkingSpots();
                    for (ParkingSpot parkingSpot : parkingSpots) {
                        if (parkingSpot.isAvailable() && isCompatible(parkingSpot, vehicleType)) {
                            parkingSpot.occupy();
                            return parkingSpot;
                        }
                    }
                }
            }
            throw new NoSpotAvailableException("Parking spot not available");
        } finally {
            lock.unlock();
        }
    }

    private boolean isCompatible(ParkingSpot parkingSpot, VehicleType vehicleType) {
        return switch (vehicleType) {
            case TWO_WHEELER -> parkingSpot.getSpotType().equals(ParkingSpotType.BIKE);
            case FOUR_WHEELER -> parkingSpot.getSpotType().equals(ParkingSpotType.CAR);
            case HEAVY_VEHICLE -> parkingSpot.getSpotType().equals(ParkingSpotType.TRUCK);
        };
    }

    public void releaseSpot(ParkingSpot parkingSpot) {
        lock.lock();
        try {
            parkingSpot.release();
        } finally {
            lock.unlock();
        }
    }

    public int getAvailableCount() {
        int count = 0;
        for (ParkingLot parkingLot : parkingLotRepository.getAllParkingLot()) {
            List<ParkingFloor> parkingFloors = parkingLot.getParkingFloors();
            for (ParkingFloor parkingFloor : parkingFloors) {
                List<ParkingSpot> parkingSpots = parkingFloor.getParkingSpots();
                for (ParkingSpot parkingSpot : parkingSpots) {
                    if (parkingSpot.isAvailable()) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
