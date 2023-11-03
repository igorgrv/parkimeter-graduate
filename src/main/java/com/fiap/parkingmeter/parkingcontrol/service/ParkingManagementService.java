package com.fiap.parkingmeter.parkingcontrol.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fiap.parkingmeter.exception.NotFoundException;
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

    // 0.083 * 60 = 5 reais
    private static final BigDecimal RATE_PER_MINUTE = BigDecimal.valueOf(0.083);

    public List<ParkingVehicle> findAll() {
        return repository.findAll();
    }

    public ParkingVehicle findByVehicleIdWithCost(String vehicleId) {
        ParkingVehicle parkingVehicle = findByVehicleId(vehicleId);
        
        // if the car is no longer parked, then do not estimate the time
        if (parkingVehicle.getEndOfOperation() != null)
            return parkingVehicle;
        
        setCost(parkingVehicle);

        return parkingVehicle;
    }

    private ParkingVehicle findByVehicleId(String vehicleId) {
        Vehicle vehicle = vehicleService.findById(vehicleId);
        return repository
                .findByVehicle(vehicle)
                .orElseThrow(
                        () -> new NotFoundException("Could not find any vehicle parked given id: " + vehicleId));
    }

    private void setCost(ParkingVehicle parkingVehicle) {
        Long minutesParked = Duration.between(parkingVehicle.getStartOfOperation(), LocalDateTime.now()).toMinutes();
        parkingVehicle.setMinutesCarParked(minutesParked);

        // cost based on the RatePerMinute rounded up - if 4,98 then it will become 5 reais
        BigDecimal cost = RATE_PER_MINUTE.multiply(new BigDecimal(minutesParked)).setScale(2, RoundingMode.CEILING);
        parkingVehicle.setCost(cost);
    }

    public ParkingVehicle startParking(String vehicleId) {
        Vehicle vehicle = vehicleService.findById(vehicleId);
        return repository.save(new ParkingVehicle(vehicle, LocalDateTime.now()));
    }

    public ParkingVehicle endParking(String vehicleId) {
        ParkingVehicle parkingVehicle = findByVehicleId(vehicleId);
        setCost(parkingVehicle);
        parkingVehicle.setEndOfOperation(LocalDateTime.now());
        return repository.save(parkingVehicle);
    }

}
