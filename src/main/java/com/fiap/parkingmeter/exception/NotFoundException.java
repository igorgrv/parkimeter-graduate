package com.fiap.parkingmeter.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class NotFoundException extends RuntimeException {

    @Getter
    private final String message;

}
