package com.apps.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.apps")
@EnableJpaRepositories(basePackages = "com.apps.repository")
@EntityScan(basePackages = "com.apps.model")
public class QuantityMeasurementAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuantityMeasurementAppApplication.class, args);
    }
}