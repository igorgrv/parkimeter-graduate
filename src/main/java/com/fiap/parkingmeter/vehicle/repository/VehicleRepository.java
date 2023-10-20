package com.fiap.parkingmeter.vehicle.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fiap.parkingmeter.driver.entity.Driver;
import com.fiap.parkingmeter.vehicle.entity.Vehicle;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {

    List<Vehicle> findByDriver(Driver driver);
}
