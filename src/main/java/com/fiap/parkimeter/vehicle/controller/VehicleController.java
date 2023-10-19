package com.fiap.parkimeter.vehicle.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.parkimeter.vehicle.entity.Vehicle;
import com.fiap.parkimeter.vehicle.service.VehicleService;

@RestController
@RequestMapping("/vehicles")
class VehicleController {

    @Autowired
    private VehicleService service;

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAll() {
        List<Vehicle> items = new ArrayList<>();
        service.findAll().forEach(items::add);
        if (items.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Vehicle> getById(@PathVariable("id") String vehicleId) {
        Vehicle existingItemOptional = service.findById(vehicleId);
        return new ResponseEntity<>(existingItemOptional, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Vehicle> create(@RequestBody Vehicle vehicle) {
        Vehicle savedItem = service.create(vehicle);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

}
