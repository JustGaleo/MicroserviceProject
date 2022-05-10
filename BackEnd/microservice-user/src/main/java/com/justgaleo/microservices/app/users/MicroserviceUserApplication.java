package com.justgaleo.microservices.app.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient 
@SpringBootApplication
@EntityScan({"com.justgaleo.microservices.app.funko.models.entity",
			"com.justgaleo.microservices.app.users.models.entity"})
public class MicroserviceUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceUserApplication.class, args);
	}

}
