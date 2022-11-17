package discogolf.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.context.annotation.Bean;
import com.fasterxml.jackson.databind.ObjectMapper;
import discoGolf.json.DiscoGolfPersistence;

/**
 * Rest Spring Boot application
 * @author Markus Johansen and Billy Barret
 * @version 1.0
 * @since 2022-10-13
 * @see DiscoGolfPersistence
 * @see DiscoRestController
 * @see DiscoRestService
 */
@SpringBootApplication
public class DiscoRestApplication {
	public static void main(final String[] args) {
		SpringApplication.run(DiscoRestApplication.class, args);
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new DiscoGolfPersistence().getMapper();
	}
}
