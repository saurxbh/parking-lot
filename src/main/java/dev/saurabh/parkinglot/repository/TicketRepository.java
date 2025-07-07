package dev.saurabh.parkinglot.repository;

import dev.saurabh.parkinglot.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
