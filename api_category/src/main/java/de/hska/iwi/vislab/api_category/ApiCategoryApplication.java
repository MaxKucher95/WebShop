package de.hska.iwi.vislab.api_category;

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
public class ApiCategoryApplication {

	private static final Logger log = LoggerFactory.getLogger(ApiCategoryApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ApiCategoryApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			log.info("Server is running...");
		};
	}

}