package com.fiap.parkingmeter.vehicle.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fiap.parkingmeter.vehicle.entity.Vehicle;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {

}
