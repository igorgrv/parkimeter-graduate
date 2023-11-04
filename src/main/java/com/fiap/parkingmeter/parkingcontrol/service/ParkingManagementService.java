package com.fiap.parkingmeter.parkingcontrol.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fiap.parkingmeter.parkingcontrol.controller.dto.ParkingVehicleStartDto;
import com.fiap.parkingmeter.parkingcontrol.entity.ParkingVehicle;
import com.fiap.parkingmeter.parkingcontrol.repository.ParkingVehicleRepository;
import com.fiap.parkingmeter.vehicle.entity.Vehicle;
import com.fiap.parkingmeter.vehicle.service.VehicleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParkingManagementService {

    private final VehicleService vehicleService;
    private final ParkingVehicleRepository repository;

    public List<ParkingVehicle> findAll() {
        return repository.findAll();
    }

    public ParkingVehicle findByVehicleIdWithCostAndActive(String vehicleId) {
        Optional<ParkingVehicle> parkingVehicleOpt = findActiveParkedVehicle(vehicleId);
        if (!parkingVehicleOpt.isPresent())
            throw new IllegalStateException("No vehicle with this ID is parked");

        ParkingVehicle parkingVehicle = parkingVehicleOpt.get();
        ParkingVehicle.setHoursParked(parkingVehicle);
        // if there is already cost then the parking type is fixed and the price won't change
        if (parkingVehicle.getCost() != null)
            return parkingVehicle;

        ParkingVehicle.setCostBasedOnHours(parkingVehicle);
        return parkingVehicle;
    }

    private List<ParkingVehicle> findByVehicleId(String vehicleId) {
        Vehicle vehicle = vehicleService.findById(vehicleId);
        return repository.findByVehicle(vehicle);
    }

    public ParkingVehicle startParking(ParkingVehicleStartDto parkingVehicleDto) {

        // if the vehicle is already parked, or does not exist, then stop the process
        Optional<ParkingVehicle> parkingVehicleOpt = findActiveParkedVehicle(parkingVehicleDto.vehicleId());
        if (parkingVehicleOpt.isPresent())
            throw new IllegalStateException("Vehicle is already parked");

        // if the driver does not have a payment method, stop the process
        Vehicle vehicle = vehicleService.findById(parkingVehicleDto.vehicleId());
        if (vehicle.getDriver().getPreferredPaymentMethod() == null)
            throw new IllegalStateException(
                    "Driver does not have a payment method associated, please, register a payment method first");

        ParkingVehicle parkingVehicle = new ParkingVehicle(vehicle, LocalDateTime.now(), parkingVehicleDto);
        return repository.save(parkingVehicle);
    }

    public ParkingVehicle endParking(String vehicleId) {
        Optional<ParkingVehicle> parkingVehicleOpt = findActiveParkedVehicle(vehicleId);
        if (!parkingVehicleOpt.isPresent())
            throw new IllegalStateException("Vehicle is no longer parked");

        ParkingVehicle parkingVehicle = parkingVehicleOpt.get();
        parkingVehicle.setIsActive(false);
        parkingVehicle.setEndOfOperation();
        ParkingVehicle.setHoursParked(parkingVehicle);

        if (parkingVehicle.getCost() != null)
            return repository.save(parkingVehicle);

        ParkingVehicle.setCostBasedOnHours(parkingVehicle);
        return repository.save(parkingVehicle);
    }

    private Optional<ParkingVehicle> findActiveParkedVehicle(String vehicleId) {
        return findByVehicleId(vehicleId).stream().filter(ParkingVehicle::getIsActive).findFirst();
    }

}
