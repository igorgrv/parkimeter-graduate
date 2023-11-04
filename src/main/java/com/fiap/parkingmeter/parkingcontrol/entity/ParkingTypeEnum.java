package com.fiap.parkingmeter.parkingcontrol.entity;

public enum ParkingTypeEnum {
    FIX("FIX"), PER_HOUR("PER_HOUR");

    private String type;

    ParkingTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
