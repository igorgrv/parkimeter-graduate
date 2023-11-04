package com.fiap.parkingmeter.parkingcontrol.controller.dto;

import com.fiap.parkingmeter.parkingcontrol.entity.ParkingFixTypePriceEnum;
import com.fiap.parkingmeter.parkingcontrol.entity.ParkingTypeEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ParkingVehicleStartDto(
        @NotBlank(message = "vehicleId is mandatory")
        @Size(min = 23, max = 30, message = "size must be between {min} and {max}")
        @Schema(description = "vehicleId to control the parking time", example = "65329a3d6470235992974c62")
        String vehicleId,

        @NotNull(message = "parkingType is mandatory")
        @Schema(description = "Parking Type defines which type of parking the driver wants", example = "FIX")
        ParkingTypeEnum parkingType,
        ParkingFixTypePriceEnum fixType) {

}
