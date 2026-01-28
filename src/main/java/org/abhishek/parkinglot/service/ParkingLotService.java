package org.abhishek.parkinglot.service;

import org.abhishek.parkinglot.enums.PaymentMode;
import org.abhishek.parkinglot.model.ParkingSpot;
import org.abhishek.parkinglot.model.Payment;
import org.abhishek.parkinglot.model.Ticket;
import org.abhishek.parkinglot.model.Vehicle;
import org.abhishek.parkinglot.repository.TicketRepository;

public class ParkingLotService {

    private final ParkingSpotManager parkingSpotManager;
    private final TicketRepository ticketRepository;
    private final PaymentService paymentService;

    public ParkingLotService(ParkingSpotManager parkingSpotManager, TicketRepository ticketRepository, PaymentService paymentService) {
        this.parkingSpotManager = parkingSpotManager;
        this.ticketRepository = ticketRepository;
        this.paymentService = paymentService;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = parkingSpotManager.findAndReserveSpot(vehicle.getVehicleType());
        Ticket ticket = new Ticket(vehicle, spot);
        ticketRepository.saveTicket(ticket);
        return ticket;
    }

    public Payment unParkVehicle(String id, PaymentMode paymentMode) {
        Ticket ticket = ticketRepository.getTicket(id);
        ticket.close();
        Payment payment = paymentService.completePayment(ticket, paymentMode);
        parkingSpotManager.releaseSpot(ticket.getParkingSpot());
        return payment;
    }
}
