package com.fiap.parkingmeter.driver.controller.dto;

import org.hibernate.validator.constraints.br.CPF;

import com.fiap.parkingmeter.driver.entity.Driver;
import com.fiap.parkingmeter.payment.PaymentEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(title = "DriverDto", description = "Object that represents a Driver's data transfer object")
public record DriverDto(
    @NotBlank(message = "fullName is mandatory")
    @Size(min = 2, max = 50, message = "size must be between {min} and {max}")
    @Schema(description = "fullName to identify the user", example = "José Fulano")
    String fullName,

    @NotBlank(message = "cannot be null or empty")
    @Size(min = 11, max = 11, message = "must have {min} characters")
    @CPF
    @Schema(description = "CPF to log in to the system", example = "00911719032")
    String licenseNumber,

    @NotNull(message = "phoneNumber is mandatory")
    @Pattern(regexp = "\\(\\d{2}\\)\\s\\d{5}-\\d{4}", message = "Phone number invalid. Valid format (99) 99999-9999")
    @Schema(description = "phoneNumber to contact the user", example = "(11) 91234-5678")
    String phoneNumber,

    @NotBlank(message = "email is mandatory")
    @Schema(description = "email to register the user", example = "jose@example.com")
    @Email
    String email,
    
    @NotBlank(message = "password is mandatory")
    @Size(min = 6, max = 15, message = "must have {min} and {max} characters")
    @Schema(description = "password to log in to the system", example = "strongPass123!")
    String password,
    
    @NotNull(message = "preferredPaymentMethod is mandatory")
    @Schema(description = "Preferred Payment Method", example = "PIX")
    PaymentEnum preferredPaymentMethod) {

    public Driver getDriverUpdated(Driver oldDriver) {
        oldDriver.setPhoneNumber(phoneNumber);
        oldDriver.setFullName(fullName);
        oldDriver.setEmail(email);
        oldDriver.setPassword(password);
        oldDriver.setPreferredPaymentMethod(preferredPaymentMethod);
        return oldDriver;
    }

}
