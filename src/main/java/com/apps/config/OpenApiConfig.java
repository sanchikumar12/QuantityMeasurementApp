package com.apps.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title       = "Quantity Measurement API",
        version     = "1.0.0",
        description = "Spring Boot REST Service for Quantity Measurements — UC17",
        contact     = @Contact(name = "Quantity Measurement Team"),
        license     = @License(name = "Apache 2.0")
    ),
    servers = {
        @Server(url = "http://localhost:8080", description = "Local development server")
    }
)
public class OpenApiConfig {
    // SpringDoc auto-configures Swagger UI; this class only adds metadata.
}