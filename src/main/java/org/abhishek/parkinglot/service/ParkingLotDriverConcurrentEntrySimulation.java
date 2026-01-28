package org.abhishek.parkinglot.service;

import org.abhishek.parkinglot.enums.ParkingSpotType;
import org.abhishek.parkinglot.enums.VehicleType;
import org.abhishek.parkinglot.exception.NoSpotAvailableException;
import org.abhishek.parkinglot.model.*;
import org.abhishek.parkinglot.repository.ParkingLotRepository;
import org.abhishek.parkinglot.repository.TicketRepository;
import org.abhishek.parkinglot.strategy.impl.HourlyRateStrategy;
import org.abhishek.parkinglot.strategy.impl.TwoWheelerPriceStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParkingLotDriverConcurrentEntrySimulation {

    public static void main(String[] args) {

        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();

        ParkingSpot parkingSpot = new ParkingSpot("B1", ParkingSpotType.BIKE);
        ParkingSpot parkingSpot1 = new ParkingSpot("C1", ParkingSpotType.CAR);
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


        ExecutorService executor = Executors.newFixedThreadPool(5);

        Runnable entryTask = () -> {

            try {
                Vehicle vehicle = new Vehicle(
                        Thread.currentThread().getName() +
                                "KA-" + Math.random(),
                        VehicleType.FOUR_WHEELER
                );

                Ticket ticket = parkingLotService.parkVehicle(vehicle);
                System.out.println(
                        Thread.currentThread().getName()
                                + " parked at "
                                + ticket.getParkingSpot().getName()
                );
            } catch (NoSpotAvailableException e) {
                System.out.println(
                        Thread.currentThread().getName()
                                + " failed: parking full"
                );
            }
        };

        for (int i = 0; i < 5; i++) {
            executor.submit(entryTask);
        }
        executor.shutdown();
    }
}
