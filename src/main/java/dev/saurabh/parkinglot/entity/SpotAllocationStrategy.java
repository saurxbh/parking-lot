package dev.saurabh.parkinglot.entity;

public interface SpotAllocationStrategy {
    ParkingSpot findSpot(ParkingLot lot, Vehicle vehicle);
}
