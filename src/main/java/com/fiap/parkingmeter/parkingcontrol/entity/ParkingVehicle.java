package com.fiap.parkingmeter.parkingcontrol.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fiap.parkingmeter.parkingcontrol.controller.dto.ParkingVehicleStartDto;
import com.fiap.parkingmeter.vehicle.entity.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingVehicle {

    @Id
    private String id;

    @DBRef
    private Vehicle vehicle;
    private LocalDateTime startOfOperation;
    private LocalDateTime endOfOperation;
    private Boolean isActive;
    private Long hoursCarParked;
    private ParkingTypeEnum parkingType;
    private ParkingFixTypePriceEnum fixType;
    private BigDecimal cost;

    private static final BigDecimal RATE_PER_HOUR = BigDecimal.TEN;

    // This Constructor is responsible for starting the parking meter
    public ParkingVehicle(Vehicle vehicle, LocalDateTime startOfOperation, ParkingVehicleStartDto parkingVehicleDto) {
        this.vehicle = vehicle;
        this.startOfOperation = startOfOperation;
        this.endOfOperation = null;
        this.isActive = true;
        this.hoursCarParked = 0L;
        this.parkingType = parkingVehicleDto.parkingType();

        if (parkingType.getType().equalsIgnoreCase("FIX") && parkingVehicleDto.fixType() != null) {
            this.fixType = parkingVehicleDto.fixType();
            this.cost = parkingVehicleDto.fixType().getPrice();

            Integer minutesToEnd = parkingVehicleDto.fixType().getMinutes();
            this.endOfOperation = this.startOfOperation.plusMinutes(minutesToEnd);
        } else if (parkingType.getType().equalsIgnoreCase("FIX") && parkingVehicleDto.fixType() == null)
            throw new IllegalArgumentException("If the Parking type is FIX then your must specify a valid Parking Fix Type");
        else
            this.cost = BigDecimal.ZERO;
    }

    public static void setHoursParked(ParkingVehicle parkingVehicle) {
        Long hoursParked = Duration.between(parkingVehicle.getStartOfOperation(), LocalDateTime.now()).toHours();
        parkingVehicle.setHoursCarParked(hoursParked);
    }


    public static void setCostBasedOnHours(ParkingVehicle parkingVehicle) {
        BigDecimal hoursParked = new BigDecimal(parkingVehicle.getHoursCarParked());
        // cost based on the RatePerHour rounded up - if 4,98 then it will become 5 reais
        BigDecimal cost = RATE_PER_HOUR.multiply(hoursParked).setScale(2, RoundingMode.CEILING);
        parkingVehicle.setCost(cost);
    }

    public void setEndOfOperation() {
        this.endOfOperation = LocalDateTime.now();
    }

}
