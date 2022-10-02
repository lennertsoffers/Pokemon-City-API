package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.validation.BuildableBuildLocationConstraint;
import com.lennertsoffers.pokemon_city_api.validation.BuildableBuildLockedConstraint;
import com.lennertsoffers.pokemon_city_api.validation.BuildableBuildEnoughFundsConstraint;
import com.lennertsoffers.pokemon_city_api.validation.BuildableTypeConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@BuildableBuildLocationConstraint
@BuildableBuildLockedConstraint
@BuildableBuildEnoughFundsConstraint
public record BuildableBuildDto(
        @NotBlank String name,
        @NotNull Integer x,
        @NotNull Integer y,
        @BuildableTypeConstraint String buildableType
) {}
