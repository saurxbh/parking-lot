package dev.saurabh.parkinglot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.saurabh.parkinglot.entity.Vehicle;
import dev.saurabh.parkinglot.enums.VehicleType;
import dev.saurabh.parkinglot.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VehicleController.class)
@AutoConfigureMockMvc(addFilters = false)
class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldRegisterVehicle() throws Exception {
        // Given
        Vehicle inputVehicle = new Vehicle();
        inputVehicle.setVehicleNumber("ABC123");
        inputVehicle.setVehicleType(VehicleType.CAR);

        Vehicle savedVehicle = new Vehicle();
        savedVehicle.setId(1L);
        savedVehicle.setVehicleNumber("ABC123");
        savedVehicle.setVehicleType(VehicleType.CAR);

        Mockito.when(vehicleService.registerVehicle(any(Vehicle.class))).thenReturn(savedVehicle);

        // When & Then
        mockMvc.perform(post("/api/vehicles/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputVehicle)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.vehicleNumber").value("ABC123"))
                .andExpect(jsonPath("$.vehicleType").value("CAR"));
    }
}