package com.fiap.parkimeter;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Parkimeter Monitor", version = "0.0.1", description = "Solution for monitoring parkimeters", license = @License(name = "MIT License", url = "https://github.com/igorgrv/parkimeter-graduate")), servers = {
        @Server(url = "http://localhost:8080") })
public class SpringDocConfigurations {
}
