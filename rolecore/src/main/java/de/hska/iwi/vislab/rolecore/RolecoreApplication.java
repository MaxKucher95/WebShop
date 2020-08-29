package de.hska.iwi.vislab.rolecore;

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

@EnableEurekaClient
@SpringBootApplication
@EnableCircuitBreaker
public class RolecoreApplication extends WebSecurityConfigurerAdapter {
  private static final Logger log = LoggerFactory.getLogger(RolecoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RolecoreApplication.class, args);
  }
  
  //This here is to prevent the default Spring Boot Login form...
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
      // Disable CSRF
      httpSecurity.csrf().disable()
              // Permit all requests without authentication
              .authorizeRequests().anyRequest().permitAll();
  }

  @Bean
	public CommandLineRunner demo(RoleRepository repository) {
		return (args) -> {
			// save a few Roles
			repository.save(new Role("admin", 0));
			repository.save(new Role("user", 1));

			// fetch all Roles
			log.info("Roles found with findAll():");
			log.info("-------------------------------");
			for (Role role : repository.findAll()) {
				log.info(role.toString());
			}
			log.info("");

			// fetch an individual Role by ID
			Role role = repository.findById(1);
			log.info("Role found with findById(1):");
			log.info("--------------------------------");
			log.info(role.toString());
			log.info("");
		};
	}
}
