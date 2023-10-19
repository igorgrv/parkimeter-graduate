package com.fiap.parkimeter.vehicle.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Vehicle {

    @Id
    private String id;
    private String name;
    private String biography;
    private String image;

}
