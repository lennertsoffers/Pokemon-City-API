package com.lennertsoffers.pokemon_city_api;

import com.lennertsoffers.pokemon_city_api.model.Role;
import com.lennertsoffers.pokemon_city_api.model.User;
import com.lennertsoffers.pokemon_city_api.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PokemonCityApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemonCityApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null, "John Travolta", "1234"));
			userService.saveUser(new User(null, "Will Smith", "1234"));
			userService.saveUser(new User(null, "Jim Carry", "1234"));
			userService.saveUser(new User(null, "Arnold Schwarzenegger", "1234"));

			userService.addRoleToUser("John Travolta", "ROLE_USER");
			userService.addRoleToUser("John Travolta", "ROLE_MANAGER");
			userService.addRoleToUser("Will Smith", "ROLE_MANAGER");
			userService.addRoleToUser("Jim Carry", "ROLE_ADMIN");
			userService.addRoleToUser("Arnold Schwarzenegger", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("Arnold Schwarzenegger", "ROLE_ADMIN");
			userService.addRoleToUser("Arnold Schwarzenegger", "ROLE_USER");
		};
	}
}
