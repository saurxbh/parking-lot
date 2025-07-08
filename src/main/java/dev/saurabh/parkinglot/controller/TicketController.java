package dev.saurabh.parkinglot.controller;

import dev.saurabh.parkinglot.entity.Ticket;
import dev.saurabh.parkinglot.enums.VehicleType;
import dev.saurabh.parkinglot.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/create")
    public ResponseEntity<Ticket> createTicket(
            @RequestParam String vehicleNo,
            @RequestParam VehicleType vehicleType,
            @RequestParam Long gateId
    ) {
        Ticket ticket = ticketService.createTicket(vehicleNo, vehicleType, gateId);
        return ResponseEntity.ok(ticket);
    }
}