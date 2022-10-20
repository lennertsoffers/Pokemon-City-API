package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.validation.BuildableBuildLocationConstraint;
import com.lennertsoffers.pokemon_city_api.validation.BuildableBuildLockedConstraint;
import com.lennertsoffers.pokemon_city_api.validation.BuildableBuildEnoughFundsConstraint;
import com.lennertsoffers.pokemon_city_api.validation.BuildableTypeConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>DTO containing data to create a new buildable</p>
 * @param name Name of the buildable
 * @param x X-Position of the buildable
 * @param y Y-Position of the buildable
 * @param buildableType BuildableType of the buildable
 */
@BuildableBuildLocationConstraint
@BuildableBuildLockedConstraint
@BuildableBuildEnoughFundsConstraint
public record BuildableBuildDto(
        @NotBlank String name,
        @NotNull Integer x,
        @NotNull Integer y,
        @BuildableTypeConstraint String buildableType
) {}
