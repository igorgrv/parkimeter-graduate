package com.fiap.parkimeter.vehicle.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fiap.parkimeter.vehicle.entity.Vehicle;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {

}
