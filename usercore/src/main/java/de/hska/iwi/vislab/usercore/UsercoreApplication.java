package de.hska.iwi.vislab.usercore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableEurekaClient
@SpringBootApplication
@EnableCircuitBreaker
public class UsercoreApplication extends WebSecurityConfigurerAdapter {

  private static final Logger log = LoggerFactory.getLogger(UsercoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UsercoreApplication.class, args);
	}

  @Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
			// save a few Users
			repository.save(new User("Max123", "Max", "Mustermann", "password123", 1));
			repository.save(new User("Sabine123", "Sabine", "Mustermann", "password123", 1));
			repository.save(new User("Hana123", "Hana", "Mustermann", "password123", 1));
			repository.save(new User("admin", "admin", "admin", "admin", 0));

			// fetch all Users
			log.info("Users found with findAll():");
			log.info("-------------------------------");
			for (User prod : repository.findAll()) {
				log.info(prod.toString());
			}
			log.info("");

			// fetch an individual User by ID
			User product = repository.findById(1);
			log.info("User found with findById(1):");
			log.info("--------------------------------");
			log.info(product.toString());
			log.info("");

			// fetch User by name
			log.info("User found with findByUsername('Sabine123'):");
			log.info("--------------------------------------------");

			log.info(repository.findByUsername("Sabine123").toString());

			// // fetch all Users
			// log.info("Users found with findAll():");
			// log.info("--------------------------------------------");
			// repository.findAll().forEach(user -> {
			// 	log.info(user.toString());
			// });

		};
  }
  
    //This here is to prevent the default Spring Boot Login form...
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // Disable CSRF
        httpSecurity.csrf().disable()
                // Permit all requests without authentication
                .authorizeRequests().anyRequest().permitAll();
    }
}
