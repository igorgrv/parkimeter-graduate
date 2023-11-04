package com.fiap.parkingmeter.parkingcontrol.entity;

import java.math.BigDecimal;

public enum ParkingFixTypePriceEnum {
    THIRTY_MINUTES(BigDecimal.valueOf(5)),
    ONE_HOUR(BigDecimal.valueOf(8)),
    THREE_HOURS(BigDecimal.valueOf(30)),
    WHOLE_DAY(BigDecimal.valueOf(50));

    private BigDecimal price;

    ParkingFixTypePriceEnum(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
