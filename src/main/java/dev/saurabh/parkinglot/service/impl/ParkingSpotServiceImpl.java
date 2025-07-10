package dev.saurabh.parkinglot.service.impl;

import dev.saurabh.parkinglot.entity.ParkingSpot;
import dev.saurabh.parkinglot.enums.Status;
import dev.saurabh.parkinglot.enums.VehicleType;
import dev.saurabh.parkinglot.repository.ParkingSpotRepository;
import dev.saurabh.parkinglot.service.ParkingSpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingSpotServiceImpl implements ParkingSpotService {

    private final ParkingSpotRepository parkingSpotRepository;

    @Override
    public Optional<ParkingSpot> getAvailableSpot(VehicleType vehicleType) {
        return parkingSpotRepository
                .findFirstByVehicleTypeAndStatus(vehicleType, Status.AVAILABLE);
    }
}