package org.abhishek.parkinglot.service;

import org.abhishek.parkinglot.enums.ParkingSpotType;
import org.abhishek.parkinglot.enums.PaymentMode;
import org.abhishek.parkinglot.enums.VehicleType;
import org.abhishek.parkinglot.model.*;
import org.abhishek.parkinglot.repository.ParkingLotRepository;
import org.abhishek.parkinglot.repository.TicketRepository;
import org.abhishek.parkinglot.strategy.impl.HourlyRateStrategy;
import org.abhishek.parkinglot.strategy.impl.TwoWheelerPriceStrategy;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotDriver {

    public static void main(String[] args) {

        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();

        ParkingSpot parkingSpot = new ParkingSpot("B1", ParkingSpotType.BIKE);
        ParkingSpot parkingSpot1 = new ParkingSpot("B2", ParkingSpotType.BIKE);
        ParkingSpot parkingSpot2 = new ParkingSpot("C2", ParkingSpotType.CAR);

        ParkingFloor parkingFloor = new ParkingFloor("F", new ArrayList<>(List.of(parkingSpot, parkingSpot1, parkingSpot2)));
        ParkingLot parkingLot = new ParkingLot(new ArrayList<>(List.of(parkingFloor)));
        parkingLotRepository.saveParkingLot(parkingLot);

        ParkingSpotManager parkingSpotManager = new ParkingSpotManager(parkingLotRepository);

        HourlyRateStrategy hourlyRateStrategy = new HourlyRateStrategy(20);
        TwoWheelerPriceStrategy twoWheelerPriceStrategy = new TwoWheelerPriceStrategy(hourlyRateStrategy);
        PaymentService paymentService = new PaymentService(twoWheelerPriceStrategy);

        TicketRepository ticketRepository = new TicketRepository();

        ParkingLotService parkingLotService = new ParkingLotService(parkingSpotManager, ticketRepository, paymentService);

        Vehicle vehicle = new Vehicle("BR05AZ-7123", VehicleType.FOUR_WHEELER);

        //park
        Ticket ticket = parkingLotService.parkVehicle(vehicle);

        Payment payment = parkingLotService.unParkVehicle(ticket.getId(), PaymentMode.CASH);

        System.out.println("Paid amount: " + payment.getAmount());

    }
}
