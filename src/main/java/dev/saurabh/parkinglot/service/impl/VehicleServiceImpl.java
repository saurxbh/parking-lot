package dev.saurabh.parkinglot.service.impl;

import dev.saurabh.parkinglot.entity.Vehicle;
import dev.saurabh.parkinglot.enums.VehicleType;
import dev.saurabh.parkinglot.repository.VehicleRepository;
import dev.saurabh.parkinglot.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Override
    public Vehicle getOrCreateVehicle(String vehicleNo, VehicleType vehicleType) {
        return vehicleRepository.findByVehicleNumber(vehicleNo)
                .orElseGet(() -> {
                    Vehicle newVehicle = Vehicle.builder()
                            .vehicleNumber(vehicleNo)
                            .vehicleType(vehicleType)
                            .build();
                    return vehicleRepository.save(newVehicle);
                });
    }

    @Override
    public Vehicle registerVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
}