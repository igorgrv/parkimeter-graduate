package com.fiap.parkingmeter.alert.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fiap.parkingmeter.alert.entity.AlertLog;
import com.fiap.parkingmeter.alert.repository.AlertLogRepository;
import com.fiap.parkingmeter.driver.entity.Driver;
import com.fiap.parkingmeter.parkingcontrol.entity.ParkingVehicle;
import com.fiap.parkingmeter.parkingcontrol.service.ParkingManagementService;
import com.fiap.parkingmeter.vehicle.entity.Vehicle;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlertService {

    private final ParkingManagementService parkingManagementService;
    private final AlertLogRepository alertLogRepository;

    public void sendAlertToDrivers() {
        List<ParkingVehicle> parkedVehicles = parkingManagementService.getVehiclesParkedWithFixType();
        if (parkedVehicles.isEmpty())
            return;

        parkedVehicles.forEach(parkedVehicle -> {

            LocalDateTime currentTime = LocalDateTime.now();
            long timeUntilEndOfOperation = Duration.between(currentTime, parkedVehicle.getEndOfOperation()).toMinutes();

            // if already expired, or if it's more than 10 minutes to expire, stop the process
            if (timeUntilEndOfOperation > 10 || timeUntilEndOfOperation < 0)
                return;

            Vehicle vehicle = parkedVehicle.getVehicle();
            Driver driver = vehicle.getDriver();

            String warningMessage = String.format(
                    "Hi %s, your parking meter is about to expire (expires at: %s), for the vehicle: %s - License Plate: %s. Renew if necessary.",
                    driver.getFullName(), parkedVehicle.getEndOfOperation(),vehicle.getBrandName(), vehicle.getLicensePlate());

            AlertLog alertLog = new AlertLog(driver, driver.getPhoneNumber(), warningMessage);
            
            // TO DO: integrate SMS with a third party service
            log.info("Sending SMS notification to driver: {} - Text message: {}", driver.getFullName(), warningMessage);
            alertLogRepository.save(alertLog);
        });
    }

}
