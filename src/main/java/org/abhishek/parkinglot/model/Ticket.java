package org.abhishek.parkinglot.model;

import org.abhishek.parkinglot.enums.TicketStatusEnum;
import org.abhishek.parkinglot.exception.AlreadyUnparkedException;

import java.time.LocalDateTime;
import java.util.UUID;


public class Ticket {
    private String id;
    private LocalDateTime entryAt;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;
    private LocalDateTime exitAt;
    private Payment payment;
    private TicketStatusEnum status;



    public Ticket(Vehicle vehicle, ParkingSpot parkingSpot) {
        this.id = UUID.randomUUID().toString();
        this.entryAt = LocalDateTime.now();
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.status = TicketStatusEnum.OPEN;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getEntryAt() {
        return entryAt;
    }

    public void setEntryAt(LocalDateTime entryAt) {
        this.entryAt = entryAt;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    public LocalDateTime getExitAt() {
        return exitAt;
    }

    public void setExitAt(LocalDateTime exitAt) {
        this.exitAt = exitAt;
    }

    public synchronized void close() {
        this.setExitAt(LocalDateTime.now());
        if(TicketStatusEnum.CLOSE.equals(this.status)) {
           throw new AlreadyUnparkedException("Vehicle is already un parked");
        }
        this.status = TicketStatusEnum.CLOSE;
    }
}
