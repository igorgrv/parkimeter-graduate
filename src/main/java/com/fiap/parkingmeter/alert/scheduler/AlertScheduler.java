package com.fiap.parkingmeter.alert.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fiap.parkingmeter.alert.service.AlertService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class AlertScheduler {

    private final AlertService service;

    @Scheduled(fixedDelay = 10000)
    public void runDeleteLogDeliveryEmail() {
        log.info("Checking Drivers that are close to the end of their parking ticket");
        service.sendAlertToDrivers();
    }
}
