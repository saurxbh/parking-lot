package dev.saurabh.parkinglot.service.impl;

import dev.saurabh.parkinglot.entity.Bill;
import dev.saurabh.parkinglot.entity.Ticket;
import dev.saurabh.parkinglot.enums.Status;
import dev.saurabh.parkinglot.repository.BillRepository;
import dev.saurabh.parkinglot.repository.TicketRepository;
import dev.saurabh.parkinglot.service.BillService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final TicketRepository ticketRepository;
    private final BillRepository billRepository;

    @Override
    @Transactional
    public Bill generateBill(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found with id: " + ticketId));

        if (ticket.getExitTime() != null) {
            throw new IllegalStateException("Bill already generated for ticket: " + ticketId);
        }

        LocalDateTime exitTime = LocalDateTime.now();
        ticket.setExitTime(exitTime);

        // Simple billing logic: ₹10 per hour
        Duration duration = Duration.between(ticket.getEntryTime(), exitTime);
        long hours = Math.max(1, duration.toHours());
        double amount = hours * 10.0;

        Bill bill = Bill.builder()
                .ticket(ticket)
                .exitTime(exitTime)
                .amount(amount)
                .build();

        ticket.getParkingSpot().setStatus(Status.AVAILABLE);

        ticketRepository.save(ticket); // Also saves updated parking spot
        return billRepository.save(bill);
    }
}