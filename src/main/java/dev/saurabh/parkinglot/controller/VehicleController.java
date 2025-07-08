package dev.saurabh.parkinglot.controller;

import dev.saurabh.parkinglot.entity.Vehicle;
import dev.saurabh.parkinglot.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping("/register")
    public ResponseEntity<Vehicle> registerVehicle(@RequestBody Vehicle vehicle) {
        Vehicle registered = vehicleService.registerVehicle(vehicle);
        return ResponseEntity.ok(registered);
    }
}