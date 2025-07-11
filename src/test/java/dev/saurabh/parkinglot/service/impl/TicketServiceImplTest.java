package dev.saurabh.parkinglot.service.impl;

import dev.saurabh.parkinglot.entity.*;
import dev.saurabh.parkinglot.enums.Status;
import dev.saurabh.parkinglot.enums.VehicleType;
import dev.saurabh.parkinglot.repository.GateRepository;
import dev.saurabh.parkinglot.repository.TicketRepository;
import dev.saurabh.parkinglot.service.ParkingSpotService;
import dev.saurabh.parkinglot.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TicketServiceImplTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private ParkingSpotService parkingSpotService;

    @Mock
    private VehicleService vehicleService;

    @Mock
    private GateRepository gateRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;

    public TicketServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTicket_Success() {
        // Given
        String vehicleNo = "MH12AB1234";
        VehicleType type = VehicleType.CAR;
        Long gateId = 1L;

        Gate gate = new Gate();
        gate.setId(gateId);

        ParkingSpot spot = new ParkingSpot();
        spot.setId(1L);
        spot.setStatus(Status.AVAILABLE);

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber(vehicleNo);
        vehicle.setVehicleType(type);

        Ticket expectedTicket = new Ticket();
        expectedTicket.setVehicle(vehicle);
        expectedTicket.setParkingSpot(spot);
        expectedTicket.setEntryGate(gate);

        when(gateRepository.findById(gateId)).thenReturn(Optional.of(gate));
        when(parkingSpotService.getAvailableSpot(type)).thenReturn(Optional.of(spot));
        when(vehicleService.getOrCreateVehicle(vehicleNo, type)).thenReturn(vehicle);
        when(ticketRepository.save(any(Ticket.class))).thenReturn(expectedTicket);

        // When
        Ticket ticket = ticketService.createTicket(vehicleNo, type, gateId);

        // Then
        assertNotNull(ticket);
        assertEquals(vehicleNo, ticket.getVehicle().getVehicleNumber());
        assertEquals(gateId, ticket.getEntryGate().getId());
        verify(parkingSpotService).getAvailableSpot(type);
        verify(ticketRepository).save(any(Ticket.class));
    }
}