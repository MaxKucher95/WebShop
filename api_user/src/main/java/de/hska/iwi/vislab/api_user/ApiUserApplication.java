package de.hska.iwi.vislab.api_user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
@EnableCircuitBreaker
public class ApiUserApplication {

	private static final Logger log = LoggerFactory.getLogger(ApiUserApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ApiUserApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			log.info("Server is running...");
		};
	}
}