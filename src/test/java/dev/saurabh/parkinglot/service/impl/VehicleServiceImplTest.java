package dev.saurabh.parkinglot.service.impl;

import dev.saurabh.parkinglot.entity.Vehicle;
import dev.saurabh.parkinglot.enums.VehicleType;
import dev.saurabh.parkinglot.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceImplTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @Test
    void testGetOrCreateVehicle_NewVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber("KA01AB1234");
        vehicle.setVehicleType(VehicleType.CAR);

        when(vehicleRepository.findByVehicleNumber("KA01AB1234")).thenReturn(Optional.empty());
        when(vehicleRepository.save(any())).thenReturn(vehicle);

        Vehicle result = vehicleService.getOrCreateVehicle("KA01AB1234", VehicleType.CAR);

        assertThat(result.getVehicleNumber()).isEqualTo("KA01AB1234");
        verify(vehicleRepository).save(any());
    }

    @Test
    void testGetOrCreateVehicle_ExistingVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber("KA01AB1234");
        vehicle.setVehicleType(VehicleType.CAR);
        when(vehicleRepository.findByVehicleNumber("KA01AB1234")).thenReturn(Optional.of(vehicle));

        Vehicle result = vehicleService.getOrCreateVehicle("KA01AB1234", VehicleType.CAR);

        assertThat(result).isSameAs(vehicle);
        verify(vehicleRepository, never()).save(any());
    }
}