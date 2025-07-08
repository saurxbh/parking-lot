package dev.saurabh.parkinglot.repository;

import dev.saurabh.parkinglot.entity.ParkingSpot;
import dev.saurabh.parkinglot.enums.Status;
import dev.saurabh.parkinglot.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    Optional<ParkingSpot> findFirstByVehicleTypeAndStatus(VehicleType vehicleType, Status status);
}