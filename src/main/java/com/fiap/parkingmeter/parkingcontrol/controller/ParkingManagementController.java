package com.fiap.parkingmeter.parkingcontrol.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.parkingmeter.driver.entity.Driver;
import com.fiap.parkingmeter.parkingcontrol.entity.ParkingVehicle;
import com.fiap.parkingmeter.parkingcontrol.service.ParkingManagementService;
import com.fiap.parkingmeter.vehicle.entity.Vehicle;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/parking")
@Tag(name = "Parking Management", description = "Parking Time Control")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "BAD REQUEST - Client error", content = @Content(examples = {
                @ExampleObject(summary = "Bad Request", value = "{\"statusCode\":400,\"message\":\"Bad Request\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "404", description = "NOT FOUND - Vehicle Id not Found", content = @Content(examples = {
                @ExampleObject(summary = "Vehicle ID not found", value = "{\"statusCode\":404,\"message\":\"Vehicle ID not found\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something went wrong", content = @Content(examples = {
                @ExampleObject(summary = "Internal Server Error", value = "{\"statusCode\":500,\"message\":\"Internal Server Error\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE))
})
public class ParkingManagementController {

    private final ParkingManagementService service;

    @Operation(summary = "Start Parking", description = "Method to start counting the time the car is parked")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = @Content(schema = @Schema(implementation = ParkingVehicle.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @PostMapping("/start/{vehicleId}")
    public ResponseEntity<ParkingVehicle> startParking(@PathVariable("vehicleId") String vehicleId) {
        ParkingVehicle savedItem = service.startParking(vehicleId);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @Operation(summary = "Get the cost of a Vehicle", description = "Method to get the estimated cost/cost of a Vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = @Content(schema = @Schema(implementation = Vehicle.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @GetMapping("{vehicleId}")
    public ResponseEntity<ParkingVehicle> getByVehicleId(@PathVariable("vehicleId") String vehicleId) {
        ParkingVehicle vehicleWithCost = service.findByVehicleIdWithCost(vehicleId);
        return new ResponseEntity<>(vehicleWithCost, HttpStatus.OK);
    }

    @Operation(summary = "End Parking", description = "Method to end the parking vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = @Content(schema = @Schema(implementation = ParkingVehicle.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @PostMapping("/end/{vehicleId}")
    public ResponseEntity<ParkingVehicle> endParking(@PathVariable("vehicleId") String vehicleId) {
        ParkingVehicle savedItem = service.endParking(vehicleId);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

}
