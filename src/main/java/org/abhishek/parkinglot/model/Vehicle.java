package org.abhishek.parkinglot.model;

import org.abhishek.parkinglot.enums.VehicleType;

import java.util.UUID;

public class Vehicle {
    private String id;
    private String registrationId;
    private VehicleType vehicleType;

    public Vehicle(String registrationId, VehicleType vehicleType) {
        this.id = UUID.randomUUID().toString();
        this.registrationId = registrationId;
        this.vehicleType = vehicleType;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
