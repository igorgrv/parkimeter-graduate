package com.fiap.parkingmeter.vehicle.controller.dto;

import com.fiap.parkingmeter.vehicle.entity.Vehicle;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(title = "VehicleDto", description = "Object that represents a Vehicle's data transfer object")
public record VehicleDto(
    @NotBlank(message = "brandName is mandatory")
    @Size(min = 2, max = 50, message = "size must be between {min} and {max}")
    @Schema(description = "brandName to register vehicle's brand", example = "Mercedes-AMG")
    String brandName,

    @NotBlank(message = "model is mandatory")
    @Size(min = 2, max = 15, message = "size must be between {min} and {max}")
    @Schema(description = "model to register vehicle's model", example = "CLA 45")
    String model,
    
    @NotBlank(message = "licensePlate is mandatory")
    @Size(min = 7, max = 7, message = "must have {min} characters")
    @Schema(description = "licensePlate to register vehicle's license plate", example = "BRA2E23")
    String licensePlate) {

    public static VehicleDto fromEntity(Vehicle oldVehicle) {
        return new VehicleDto(oldVehicle.getBrandName(), oldVehicle.getModel(), oldVehicle.getLicensePlate());
    }

    public Vehicle getVehicleUpdated(Vehicle oldVehicle) {
        oldVehicle.setBrandName(brandName);
        oldVehicle.setModel(model);
        oldVehicle.setLicensePlate(licensePlate);
        return oldVehicle;
    }

}
