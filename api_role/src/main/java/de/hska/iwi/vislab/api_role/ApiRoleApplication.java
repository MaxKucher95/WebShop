package de.hska.iwi.vislab.api_role;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EnableCircuitBreaker
public class ApiRoleApplication {

	private static final Logger log = LoggerFactory.getLogger(ApiRoleApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ApiRoleApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			log.info("Server is running...");
		};
	}
}