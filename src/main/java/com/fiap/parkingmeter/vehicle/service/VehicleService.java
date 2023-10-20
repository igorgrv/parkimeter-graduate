package com.fiap.parkingmeter.vehicle.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fiap.parkingmeter.exception.NotFoundException;
import com.fiap.parkingmeter.vehicle.controller.dto.VehicleDto;
import com.fiap.parkingmeter.vehicle.entity.Vehicle;
import com.fiap.parkingmeter.vehicle.repository.VehicleRepository;

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
                .orElseThrow(() -> new NotFoundException("Could not find any vehicle given id: " + vehicleId));
    }

    public Vehicle create(VehicleDto vehicle) {
        return repository.save(new Vehicle(vehicle));
    }

    public Vehicle update(String id, VehicleDto vehicle) {
        Vehicle oldVehicle = findById(id);
        Vehicle updatedVehicle = vehicle.getVehicleUpdated(oldVehicle);
        return repository.save(updatedVehicle);
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

}
