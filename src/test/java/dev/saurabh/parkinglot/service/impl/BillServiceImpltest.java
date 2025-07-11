package dev.saurabh.parkinglot.service.impl;

import dev.saurabh.parkinglot.entity.*;
import dev.saurabh.parkinglot.enums.Status;
import dev.saurabh.parkinglot.enums.VehicleType;
import dev.saurabh.parkinglot.repository.BillRepository;
import dev.saurabh.parkinglot.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BillServiceImplTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private BillRepository billRepository;

    @InjectMocks
    private BillServiceImpl billService;

    private Ticket ticket;
    private ParkingSpot parkingSpot;

    @BeforeEach
    void setUp() {
        parkingSpot = new ParkingSpot();
        parkingSpot.setStatus(Status.OCCUPIED);
        parkingSpot.setVehicleType(VehicleType.CAR);
        parkingSpot.setSpotNumber("B1-01");

        ticket = new Ticket();
        ticket.setId(1L);
        ticket.setEntryTime(LocalDateTime.now().minusHours(3));
        ticket.setParkingSpot(parkingSpot);
    }

    @Test
    void testGenerateBill_Success() {
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(any())).thenReturn(ticket);
        when(billRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Bill bill = billService.generateBill(1L);

        assertThat(bill).isNotNull();
        assertThat(bill.getAmount()).isEqualTo(30.0); // 3 hours × ₹10
        assertThat(bill.getExitTime()).isNotNull();
        assertThat(bill.getTicket()).isEqualTo(ticket);
        assertThat(ticket.getParkingSpot().getStatus()).isEqualTo(Status.AVAILABLE);

        verify(ticketRepository).save(ticket);
        verify(billRepository).save(any(Bill.class));
    }

    @Test
    void testGenerateBill_TicketNotFound() {
        when(ticketRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> billService.generateBill(1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Ticket not found");

        verify(ticketRepository, never()).save(any());
        verify(billRepository, never()).save(any());
    }

    @Test
    void testGenerateBill_AlreadyExited() {
        ticket.setExitTime(LocalDateTime.now());
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

        assertThatThrownBy(() -> billService.generateBill(1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Bill already generated");

        verify(ticketRepository, never()).save(any());
        verify(billRepository, never()).save(any());
    }
}