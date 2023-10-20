package com.fiap.parkingmeter.vehicle.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.parkingmeter.vehicle.controller.dto.VehicleDto;
import com.fiap.parkingmeter.vehicle.entity.Vehicle;
import com.fiap.parkingmeter.vehicle.service.VehicleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/vehicles")
@Tag(name = "Vehicles", description = "Methods for manipulating Vehicle's data")
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
public class VehicleController {

    private final VehicleService service;

    @Operation(summary = "Get all the Vehicles", description = "Method for getting all the Vehicles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - List of all Vehicles", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Vehicle.class)), mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAll() {
        List<Vehicle> items = new ArrayList<>();
        service.findAll().forEach(items::add);
        if (items.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @Operation(summary = "Get a Vehicle by ID", description = "Method to get a Vehicle based on the ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = @Content(schema = @Schema(implementation = Vehicle.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @GetMapping("{id}")
    public ResponseEntity<Vehicle> getById(@PathVariable("id") String vehicleId) {
        Vehicle existingItemOptional = service.findById(vehicleId);
        return new ResponseEntity<>(existingItemOptional, HttpStatus.OK);
    }

    @Operation(summary = "Create an Vehicle", description = "Method to crete an new Vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - Vehicle successfully created", content = @Content(schema = @Schema(implementation = Vehicle.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @PostMapping
    public ResponseEntity<Vehicle> create(@Valid @RequestBody VehicleDto vehicle) {
        Vehicle savedItem = service.create(vehicle);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a Vehicle", description = "Method to update an existing Vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - Vehicle successfully updated", content = @Content(schema = @Schema(implementation = Vehicle.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @PutMapping("{id}")
    public ResponseEntity<Vehicle> update(@PathVariable("id") String id, @Valid @RequestBody VehicleDto vehicle) {
        Vehicle updatedVehicle = service.update(id, vehicle);
        return new ResponseEntity<>(updatedVehicle, HttpStatus.OK);
    }

    @Operation(summary = "Delete a Vehicle", description = "Method to Delete an existing Vehicle")
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        service.delete(id);
        String message = "Vehicle " + id + " deleted with success";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
