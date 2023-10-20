package com.fiap.parkingmeter.vehicle.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fiap.parkingmeter.vehicle.controller.dto.VehicleDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    private String id;
    private String brandName;
    private String model;
    private String licensePlate;
    @Version
    private Long version;

    public Vehicle(String id, VehicleDto dto) {
        this.id = id;
        this.brandName = dto.brandName();
        this.model = dto.model();
        this.licensePlate = dto.licensePlate();
    }

    public Vehicle(VehicleDto dto) {
        this.brandName = dto.brandName();
        this.model = dto.model();
        this.licensePlate = dto.licensePlate();
    }

}
