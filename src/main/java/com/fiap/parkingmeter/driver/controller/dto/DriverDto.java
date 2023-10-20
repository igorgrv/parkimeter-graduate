package com.fiap.parkingmeter.driver.controller.dto;

import com.fiap.parkingmeter.driver.entity.Driver;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(title = "DriverDto", description = "Object that represents a Driver's data transfer object")
public record DriverDto(
    @NotBlank(message = "brandName is mandatory")
    @Size(min = 2, max = 50, message = "size must be between {min} and {max}")
    @Schema(description = "brandName to log in to the system", example = "Mercedes-AMG")
    String brandName,

    @NotBlank(message = "model is mandatory")
    @Size(min = 2, max = 15, message = "size must be between {min} and {max}")
    @Schema(description = "model to log in to the system", example = "CLA 45")
    String model,
    
    @NotBlank(message = "licensePlate is mandatory")
    @Size(min = 7, max = 7, message = "must have {min} characters")
    @Schema(description = "licensePlate to log in to the system", example = "BRA2E23")
    String licensePlate) {

  public Driver getDriverUpdated(Driver oldDriver) {
    oldDriver.setBrandName(brandName);
    oldDriver.setModel(model);
    oldDriver.setLicensePlate(licensePlate);
    return oldDriver;
  }

}
