package com.fiap.parkingmeter.alert.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fiap.parkingmeter.driver.entity.Driver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class AlertLog {

    @Id
    private String id;

    @DBRef
    private Driver driver;
    private String driverContact;
    private String warningMessage;
    private LocalDateTime createdDateTime;
    
    public AlertLog(Driver driver, String driverContact, String warningMessage) {
        this.driver = driver;
        this.driverContact = driverContact;
        this.warningMessage = warningMessage;
        this.createdDateTime = LocalDateTime.now();
    }

    
}
