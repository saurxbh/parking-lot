package dev.saurabh.parkinglot.service;

import dev.saurabh.parkinglot.entity.ParkingSpot;
import dev.saurabh.parkinglot.enums.VehicleType;

import java.util.Optional;

public interface ParkingSpotService {
    Optional<ParkingSpot> getAvailableSpot(VehicleType vehicleType);
}