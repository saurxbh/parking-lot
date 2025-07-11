package dev.saurabh.parkinglot.controller;

import dev.saurabh.parkinglot.entity.Gate;
import dev.saurabh.parkinglot.entity.ParkingSpot;
import dev.saurabh.parkinglot.entity.Ticket;
import dev.saurabh.parkinglot.entity.Vehicle;
import dev.saurabh.parkinglot.enums.VehicleType;
import dev.saurabh.parkinglot.service.TicketService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TicketController.class)
@AutoConfigureMockMvc(addFilters = false)
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @Test
    void testCreateTicket() throws Exception {
        // Create mock objects
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber("KA01AB1234");
        vehicle.setVehicleType(VehicleType.CAR);

        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setVehicle(vehicle);
        ticket.setEntryTime(LocalDateTime.now());
        ticket.setParkingSpot(new ParkingSpot());
        ticket.setEntryGate(new Gate());

        // Mock service call
        when(ticketService.createTicket("KA01AB1234", VehicleType.CAR, 1L)).thenReturn(ticket);

        // Perform POST request
        mockMvc.perform(post("/api/tickets/create")
                        .param("vehicleNo", "KA01AB1234")
                        .param("vehicleType", "CAR")
                        .param("gateId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.vehicle.vehicleNumber").value("KA01AB1234"));
    }
}