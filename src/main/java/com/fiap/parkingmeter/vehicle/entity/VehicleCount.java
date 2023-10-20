package com.fiap.parkingmeter.vehicle.entity;

import lombok.Data;

@Data
public class VehicleCount {
    private Vehicle vehicle;
    private int totalArticles;
}
