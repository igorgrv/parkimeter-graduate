package com.fiap.parkingmeter.driver.controller;

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

import com.fiap.parkingmeter.driver.controller.dto.DriverDto;
import com.fiap.parkingmeter.driver.entity.Driver;
import com.fiap.parkingmeter.driver.service.DriverService;

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
@RequestMapping("/drivers")
@Tag(name = "Drivers", description = "Methods for manipulating Driver's data")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "BAD REQUEST - Client error", content = @Content(examples = {
                @ExampleObject(summary = "Bad Request", value = "{\"statusCode\":400,\"message\":\"Bad Request\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "404", description = "NOT FOUND - Driver Id not Found", content = @Content(examples = {
                @ExampleObject(summary = "Driver ID not found", value = "{\"statusCode\":404,\"message\":\"Driver ID not found\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something went wrong", content = @Content(examples = {
                @ExampleObject(summary = "Internal Server Error", value = "{\"statusCode\":500,\"message\":\"Internal Server Error\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE))
})
public class DriverController {

    private final DriverService service;

    @Operation(summary = "Get all the Drivers", description = "Method for getting all the Drivers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - List of all Drivers", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Driver.class)), mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping
    public ResponseEntity<List<Driver>> getAll() {
        List<Driver> items = new ArrayList<>();
        service.findAll().forEach(items::add);
        if (items.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @Operation(summary = "Get a Driver by ID", description = "Method to get a Driver based on the ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = @Content(schema = @Schema(implementation = Driver.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @GetMapping("{id}")
    public ResponseEntity<Driver> getById(@PathVariable("id") String driverId) {
        Driver existingItemOptional = service.findById(driverId);
        return new ResponseEntity<>(existingItemOptional, HttpStatus.OK);
    }

    @Operation(summary = "Create a Driver", description = "Method to crete a new Driver")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - Driver successfully created", content = @Content(schema = @Schema(implementation = Driver.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @PostMapping
    public ResponseEntity<Driver> create(@Valid @RequestBody DriverDto driver) {
        Driver savedItem = service.create(driver);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a Driver", description = "Method to update an existing Driver")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - Driver successfully updated", content = @Content(schema = @Schema(implementation = Driver.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @PutMapping("{id}")
    public ResponseEntity<Driver> update(@PathVariable("id") String id, @Valid @RequestBody DriverDto driver) {
        Driver updatedDriver = service.update(id, driver);
        return new ResponseEntity<>(updatedDriver, HttpStatus.OK);
    }

    @Operation(summary = "Delete a Driver", description = "Method to Delete an existing Driver")
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        service.delete(id);
        String message = "Driver " + id + " deleted with success";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
