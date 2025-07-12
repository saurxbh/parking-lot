package dev.saurabh.parkinglot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.saurabh.parkinglot.entity.Bill;
import dev.saurabh.parkinglot.entity.Ticket;
import dev.saurabh.parkinglot.service.BillService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BillController.class)
@AutoConfigureMockMvc(addFilters = false)
class BillControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BillService billService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGenerateBill() throws Exception {
        // Given
        Long ticketId = 1L;

        Ticket ticket = new Ticket();
        ticket.setId(ticketId);

        Bill bill = new Bill();
        bill.setId(101L);
        bill.setTicket(ticket);
        bill.setAmount(50.0);
        bill.setExitTime(LocalDateTime.of(2025, 7, 11, 14, 30));

        when(billService.generateBill(ticketId)).thenReturn(bill);

        // When & Then
        mockMvc.perform(post("/api/bills/generate/{ticketId}", ticketId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(101L))
                .andExpect(jsonPath("$.amount").value(50.0))
                .andExpect(jsonPath("$.exitTime").exists());
    }
}