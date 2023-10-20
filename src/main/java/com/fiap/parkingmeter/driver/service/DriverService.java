package com.fiap.parkingmeter.driver.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fiap.parkingmeter.driver.controller.dto.DriverDto;
import com.fiap.parkingmeter.driver.entity.Driver;
import com.fiap.parkingmeter.driver.repository.DriverRepository;
import com.fiap.parkingmeter.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository repository;

    public List<Driver> findAll() {
        return repository.findAll();
    }

    public Driver findById(String driverId) {
        return repository
                .findById(driverId)
                .orElseThrow(() -> new NotFoundException("Could not find any driver given id: " + driverId));
    }

    public Driver create(DriverDto driver) {
        return repository.save(new Driver(driver));
    }

    public Driver update(String id, DriverDto driver) {
        Driver oldDriver = findById(id);
        Driver updatedDriver = driver.getDriverUpdated(oldDriver);
        return repository.save(updatedDriver);
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

}
