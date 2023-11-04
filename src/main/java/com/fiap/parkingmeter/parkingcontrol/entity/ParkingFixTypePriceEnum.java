package com.fiap.parkingmeter.parkingcontrol.entity;

import java.math.BigDecimal;

public enum ParkingFixTypePriceEnum {
    THIRTY_MINUTES(BigDecimal.valueOf(5), 30),
    ONE_HOUR(BigDecimal.valueOf(8), 1 * 60),
    THREE_HOURS(BigDecimal.valueOf(30), 3 * 60),
    WHOLE_DAY(BigDecimal.valueOf(50), 24 * 60);

    private BigDecimal price;
    private Integer minutes;

    ParkingFixTypePriceEnum(BigDecimal price, Integer minutes) {
        this.price = price;
        this.minutes = minutes;
    }

    public BigDecimal getPrice() {
        return price;
    }

     public Integer getMinutes() {
        return minutes;
    }
}
