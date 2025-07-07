package dev.saurabh.parkinglot.repository;

import dev.saurabh.parkinglot.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {
}
