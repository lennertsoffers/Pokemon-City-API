package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.validation.UsernameAlreadyExistsConstraint;
import com.lennertsoffers.pokemon_city_api.validation.PasswordConstraint;
import org.hibernate.validator.constraints.Length;

public record UserCreationDto(
        @Length(min = 3, max = 20, message = "Username length must be between 3 and 20") @UsernameAlreadyExistsConstraint String username,
        @PasswordConstraint String password
) {}
