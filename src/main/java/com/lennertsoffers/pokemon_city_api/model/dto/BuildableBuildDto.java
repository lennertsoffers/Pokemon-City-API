package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.validation.BuildableBuildLocationConstraint;
import com.lennertsoffers.pokemon_city_api.validation.BuildableLockedConstraint;
import com.lennertsoffers.pokemon_city_api.validation.BuildableTypeConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@BuildableBuildLocationConstraint
@BuildableLockedConstraint
public record BuildableBuildDto(
        @NotBlank String name,
        @NotNull int x,
        @NotNull int y,
        @BuildableTypeConstraint String buildableType
) {}
