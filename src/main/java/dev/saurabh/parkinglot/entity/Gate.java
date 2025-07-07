package dev.saurabh.parkinglot.entity;

import dev.saurabh.parkinglot.enums.GateType;
import dev.saurabh.parkinglot.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Gate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int gateNumber;

    @Enumerated(EnumType.STRING)
    private GateType gateType;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_floor_id")
    private ParkingFloor parkingFloor;
}