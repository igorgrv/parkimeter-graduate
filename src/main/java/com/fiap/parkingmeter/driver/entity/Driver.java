package com.fiap.parkingmeter.driver.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fiap.parkingmeter.driver.controller.dto.DriverDto;
import com.fiap.parkingmeter.vehicle.entity.Vehicle;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Driver {

    @Id
    private String id;
    private String fullName;
    private String licenseNumber;
    private Email email;
    private String password;

    @DBRef
    private List<Vehicle> vehicles;

    @Version
    private Long version;

    public Driver(DriverDto dto) {
        this.fullName = dto.fullName();
        this.licenseNumber = dto.licenseNumber();
        this.email = dto.email();
        this.password = dto.password();
    }

}
