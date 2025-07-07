package dev.saurabh.parkinglot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int capacity;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ElementCollection(targetClass = VehicleType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "parking_lot_vehicle_types", joinColumns = @JoinColumn(name = "parking_lot_id"))
    @Column(name = "vehicle_type")
    private List<VehicleType> supportedVehicleTypes;

    @OneToMany(mappedBy = "parkingLot", fetch = FetchType.LAZY)
    private List<ParkingFloor> floors;

    // Strategy interfaces can be injected/configured as needed. For now, left abstract.
    @Transient
    private SpotAllocationStrategy spotAllocationStrategy;

    @Transient
    private BillCalculationStrategy billCalculationStrategy;
}