package dev.saurabh.parkinglot.repository;

import dev.saurabh.parkinglot.entity.ParkingSpot;
import dev.saurabh.parkinglot.enums.Status;
import dev.saurabh.parkinglot.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    List<ParkingSpot> findByStatusAndVehicleType(Status status, VehicleType vehicleType);
}
