package dev.saurabh.parkinglot.repository;

import dev.saurabh.parkinglot.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByVehicleNo(String vehicleNo);
}
