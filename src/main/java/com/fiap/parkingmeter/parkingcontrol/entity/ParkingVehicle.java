package com.fiap.parkingmeter.parkingcontrol.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private Long minutesCarParked;
    private BigDecimal cost;

    // This Constructor is responsible for starting the parking meter
    public ParkingVehicle(Vehicle vehicle, LocalDateTime startOfOperation) {
        this.vehicle = vehicle;
        this.startOfOperation = startOfOperation;
        this.endOfOperation = null;
        this.minutesCarParked = 0L;
        this.cost = BigDecimal.ZERO;
    }

    

}
