package com.fiap.parkingmeter.driver.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.fiap.parkingmeter.driver.entity.Driver;

public interface DriverRepository extends MongoRepository<Driver, String> {

}
