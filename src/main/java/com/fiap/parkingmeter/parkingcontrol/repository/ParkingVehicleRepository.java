package com.fiap.parkingmeter.parkingcontrol.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.fiap.parkingmeter.parkingcontrol.entity.ParkingVehicle;
import com.fiap.parkingmeter.vehicle.entity.Vehicle;

public interface ParkingVehicleRepository extends MongoRepository<ParkingVehicle, String> {

  List<ParkingVehicle> findByVehicle(Vehicle vehicle);

  @Query("{$and: [ {isActive: {$eq: true}}, {parkingType: {$eq: 'FIX'}} ]}")
  List<ParkingVehicle> findVehiclesParkedWithFixType();

}
