package com.fiap.parkingmeter.parkingcontrol.controller.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ParkingVehicleStartDto(
        String id,

        @NotBlank(message = "vehicleId is mandatory")
        @Size(min = 10, max = 50, message = "size must be between {min} and {max}")
        @Schema(description = "vehicleId to control the parking time", example = "65329a3d6470235992974c62")
        String vehicleId,
        LocalDateTime startOfOperation) {

}
