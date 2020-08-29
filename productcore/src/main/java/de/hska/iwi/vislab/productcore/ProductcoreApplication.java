package de.hska.iwi.vislab.productcore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableEurekaClient
@SpringBootApplication
@EnableCircuitBreaker
public class ProductcoreApplication {
	private static final Logger log = LoggerFactory.getLogger(ProductcoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProductcoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ProductRepository repository) {
		return (args) -> {
			// save a few Products
			repository.save(new Product("Apfel", 1.0, 1, "süß und saftig"));
			repository.save(new Product("Karotte", 2.0, 2, "sehr gesund"));
			repository.save(new Product("Schokobons", 3.0, 3, "sind klein und rund..."));

			// fetch all Products
			log.info("Products found with findAll():");
			log.info("-------------------------------");
			for (Product prod : repository.findAll()) {
				log.info(prod.toString());
			}
			log.info("");

			// fetch an individual Product by ID
			Product product = repository.findById(1);
			log.info("Product found with findById(1):");
			log.info("--------------------------------");
			log.info(product.toString());
			log.info("");

			// fetch Product by name
			log.info("Product found with findByName('Karotte'):");
			log.info("--------------------------------------------");
			log.info("Products found with findAll():");
			log.info("--------------------------------------------");
			repository.findAll().forEach(cat -> {
				log.info(cat.toString());
			});

		};
	}
}