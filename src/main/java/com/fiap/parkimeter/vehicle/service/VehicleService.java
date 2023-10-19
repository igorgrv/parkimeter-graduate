package com.fiap.parkimeter.vehicle.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fiap.parkimeter.vehicle.entity.Vehicle;
import com.fiap.parkimeter.vehicle.repository.VehicleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository repository;

    public List<Vehicle> findAll() {
        return repository.findAll();
    }

    public Vehicle findById(String vehicleId) {
        return repository
                .findById(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException("Could not find vehicle"));
    }

    public Vehicle create(Vehicle vehicle) {
        return repository.save(vehicle);
    }

}
