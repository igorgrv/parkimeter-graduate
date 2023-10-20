package com.fiap.parkingmeter.driver.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.fiap.parkingmeter.driver.entity.Driver;
import com.fiap.parkingmeter.vehicle.controller.dto.VehicleDto;
import com.fiap.parkingmeter.vehicle.entity.Vehicle;

public record DriverVehicleDto(
        String id,
        String fullName,
        String licenseNumber,
        String email,
        Long version,
        List<VehicleDto> vehicleList) {

    public static DriverVehicleDto fromEntities(Driver driver, List<Vehicle> vehicles) {
        List<VehicleDto> vehicleList = new ArrayList<>();
        vehicles.forEach(vehicle -> vehicleList.add(VehicleDto.fromEntity(vehicle)));
        return new DriverVehicleDto(
                driver.getId(),
                driver.getFullName(),
                driver.getLicenseNumber(),
                driver.getEmail(),
                driver.getVersion(),
                vehicleList);
    }

}
