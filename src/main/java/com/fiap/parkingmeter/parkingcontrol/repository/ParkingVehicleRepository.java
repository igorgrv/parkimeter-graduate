package com.fiap.parkingmeter.parkingcontrol.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fiap.parkingmeter.parkingcontrol.entity.ParkingVehicle;
import com.fiap.parkingmeter.vehicle.entity.Vehicle;

public interface ParkingVehicleRepository extends MongoRepository<ParkingVehicle, String> {

  Optional<ParkingVehicle> findByVehicle(Vehicle vehicle);

}
