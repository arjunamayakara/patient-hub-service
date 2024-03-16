package com.hub.patient.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PatientHubServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientHubServiceApplication.class, args);
	}

}
