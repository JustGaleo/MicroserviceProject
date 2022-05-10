package com.justgaleo.microservices.app.funko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroserviceFunkoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceFunkoApplication.class, args);
	}

}
