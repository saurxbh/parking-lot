package dev.saurabh.parkinglot.service.impl;

import dev.saurabh.parkinglot.entity.ParkingSpot;
import dev.saurabh.parkinglot.enums.Status;
import dev.saurabh.parkinglot.enums.VehicleType;
import dev.saurabh.parkinglot.repository.ParkingSpotRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ParkingSpotServiceImplTest {

    @Mock
    private ParkingSpotRepository parkingSpotRepository;

    @InjectMocks
    private ParkingSpotServiceImpl parkingSpotService;

    public ParkingSpotServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAvailableSpot_Success() {
        ParkingSpot spot = new ParkingSpot();
        spot.setVehicleType(VehicleType.CAR);
        spot.setStatus(Status.AVAILABLE);

        when(parkingSpotRepository.findFirstByVehicleTypeAndStatus(VehicleType.CAR, Status.AVAILABLE))
                .thenReturn(Optional.of(spot));

        Optional<ParkingSpot> result = parkingSpotService.getAvailableSpot(VehicleType.CAR);

        assertThat(result).isPresent();
        assertThat(result.get().getStatus()).isEqualTo(Status.AVAILABLE);
    }

    @Test
    void testFindAvailableSpot_NotFound() {
        when(parkingSpotRepository.findFirstByVehicleTypeAndStatus(VehicleType.CAR, Status.AVAILABLE))
                .thenReturn(Optional.empty());

        Optional<ParkingSpot> result = parkingSpotService.getAvailableSpot(VehicleType.CAR);

        assertThat(result).isEmpty();
    }
}