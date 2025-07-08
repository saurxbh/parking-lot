package dev.saurabh.parkinglot.service;

import dev.saurabh.parkinglot.entity.Ticket;
import dev.saurabh.parkinglot.enums.VehicleType;

public interface TicketService {
    Ticket createTicket(String vehicleNo, VehicleType vehicleType, Long entryGateId);
}