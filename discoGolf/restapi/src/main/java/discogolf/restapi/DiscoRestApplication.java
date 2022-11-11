package discogolf.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.context.annotation.Bean;
import com.fasterxml.jackson.databind.ObjectMapper;
import discoGolf.json.DiscoGolfPersistence;
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
