package com.fiap.parkingmeter.alert.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fiap.parkingmeter.alert.entity.AlertLog;

public interface AlertLogRepository extends MongoRepository<AlertLog, String> {
    
}
