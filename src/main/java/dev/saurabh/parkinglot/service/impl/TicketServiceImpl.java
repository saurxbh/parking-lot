package dev.saurabh.parkinglot.service.impl;

import dev.saurabh.parkinglot.entity.*;
import dev.saurabh.parkinglot.enums.Status;
import dev.saurabh.parkinglot.enums.VehicleType;
import dev.saurabh.parkinglot.repository.GateRepository;
import dev.saurabh.parkinglot.repository.TicketRepository;
import dev.saurabh.parkinglot.service.ParkingSpotService;
import dev.saurabh.parkinglot.service.TicketService;
import dev.saurabh.parkinglot.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final ParkingSpotService parkingSpotService;
    private final VehicleService vehicleService;
    private final GateRepository gateRepository;

    @Override
    public Ticket createTicket(String vehicleNo, VehicleType vehicleType, Long entryGateId) {
        Gate gate = gateRepository.findById(entryGateId)
                .orElseThrow(() -> new RuntimeException("Invalid gate ID"));

        ParkingSpot spot = parkingSpotService.getAvailableSpot(vehicleType)
                .orElseThrow(() -> new RuntimeException("No available spot"));

        Vehicle vehicle = vehicleService.getOrCreateVehicle(vehicleNo, vehicleType);

        // Create Ticket
        Ticket ticket = Ticket.builder()
                .vehicle(vehicle)
                .parkingSpot(spot)
                .entryGate(gate)
                .entryTime(LocalDateTime.now())
                .build();

        // Mark spot as occupied
        spot.setStatus(Status.OCCUPIED);

        return ticketRepository.save(ticket);
    }
}