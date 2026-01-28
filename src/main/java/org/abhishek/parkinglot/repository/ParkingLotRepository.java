package org.abhishek.parkinglot.repository;

import org.abhishek.parkinglot.exception.InvalidParkingLotException;
import org.abhishek.parkinglot.model.ParkingLot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotRepository {

    private final Map<String, ParkingLot> parkingLotMap = new HashMap<>();

    public void saveParkingLot(ParkingLot parkingLot) {
        parkingLotMap.put(parkingLot.getId(), parkingLot);
    }

    public ParkingLot getParkingLot(String id) {
        if (!parkingLotMap.containsKey(id)) {
            throw new InvalidParkingLotException("Parking lot not found");
        }
        return parkingLotMap.get(id);
    }

    public List<ParkingLot> getAllParkingLot() {
        if (parkingLotMap.isEmpty()) {
            throw new InvalidParkingLotException("Parking lot not available");
        }
        return parkingLotMap.values().stream().toList();
    }

}
