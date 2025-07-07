package dev.saurabh.parkinglot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String spotNumber; // e.g., "B1-05" for Floor B1, Spot 05

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType; // e.g., CAR, ELECTRIC

    @Enumerated(EnumType.STRING)
    private Status status;

    private boolean hasChargingFacility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_floor_id")
    private ParkingFloor parkingFloor;

    @OneToOne(mappedBy = "parkedSpot")
    private Vehicle currentVehicle;
}