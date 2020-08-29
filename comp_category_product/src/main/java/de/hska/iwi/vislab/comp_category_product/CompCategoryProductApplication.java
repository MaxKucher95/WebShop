package de.hska.iwi.vislab.comp_category_product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EnableCircuitBreaker
public class CompCategoryProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompCategoryProductApplication.class, args);
	}

}
