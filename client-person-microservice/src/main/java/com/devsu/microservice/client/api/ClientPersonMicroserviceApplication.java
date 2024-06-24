package com.devsu.microservice.client.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.devsu.microservice")
@EnableJpaRepositories(basePackages = "com.devsu.microservice")
public class ClientPersonMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientPersonMicroserviceApplication.class, args);
	}

}
