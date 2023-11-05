package com.fiap.parkingmeter;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Parking Meter Monitor", version = "0.0.1", description = "Solution for monitoring Parking Meters", license = @License(name = "MIT License", url = "https://github.com/igorgrv/parkingmeter-graduate")), servers = {
        @Server(url = "http://localhost:8080"),
        @Server(url = "https://localhost:8080"),
        @Server(url = "https://parkingmeter-e2kw5mlx.b4a.run")
})
public class SpringDocConfigurations {
}
