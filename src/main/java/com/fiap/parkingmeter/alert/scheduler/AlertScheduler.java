package com.fiap.parkingmeter.alert.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fiap.parkingmeter.alert.service.AlertService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AlertScheduler {

    private final AlertService service;

    /*
     * seconds minutes hours day-of-month month day-of-week
     *   0       *1      *        *         *        ?
     */
    @Scheduled(cron = "*/10 * * * * *")
    public void runDeleteLogDeliveryEmail() {
        service.sendAlertToDrivers();
    }
}
