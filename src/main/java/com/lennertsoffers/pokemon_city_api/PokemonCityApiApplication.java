package com.lennertsoffers.pokemon_city_api;

import com.lennertsoffers.pokemon_city_api.model.User;
import com.lennertsoffers.pokemon_city_api.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.lennertsoffers.pokemon_city_api.security.RoleType.*;

@SpringBootApplication
public class PokemonCityApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemonCityApiApplication.class, args);
	}

	// TODO - Remove bean for production
	@Bean
	public CommandLineRunner run(UserService userService) {
		return args -> {
			User john = new User("John Travolta", "1234");
			if (userService.getUser(john.getUsername()) == null) {
				userService.saveUser(john);
				userService.addRoleToUser(john.getUsername(), PLAYER);
			}

			User arnold = new User("Arnold Schwarzenegger", "1234");
			if (userService.getUser(arnold.getUsername()) == null) {
				userService.saveUser(arnold);
				userService.addRoleToUser(arnold.getUsername(), PLAYER);
				userService.addRoleToUser(arnold.getUsername(), ADMIN);
			}
		};
	}
}
