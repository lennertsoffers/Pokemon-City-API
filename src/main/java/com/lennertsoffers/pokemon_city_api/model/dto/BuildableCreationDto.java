package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.validation.BuildableTypeConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record BuildableCreationDto(
        @NotBlank String name,
        @NotNull int x,
        @NotNull int y,
        @BuildableTypeConstraint String buildableType
) {}
