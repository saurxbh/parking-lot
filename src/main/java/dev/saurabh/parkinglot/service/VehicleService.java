package dev.saurabh.parkinglot.service;

import dev.saurabh.parkinglot.entity.Vehicle;
import dev.saurabh.parkinglot.enums.VehicleType;

public interface VehicleService {
    Vehicle getOrCreateVehicle(String vehicleNo, VehicleType vehicleType);
    Vehicle registerVehicle(Vehicle vehicle);
}