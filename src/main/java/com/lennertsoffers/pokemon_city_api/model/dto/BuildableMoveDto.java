package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.validation.BuildableMoveConstraint;
import com.lennertsoffers.pokemon_city_api.validation.NotMoveRoadConstraint;

import javax.validation.constraints.NotNull;

/**
 * <p>DTO containing the data needed to move a Buildable</p>
 * @param id The id of the buildable
 * @param x The new x-position of the buildable
 * @param y The new y-position of the buildable
 */
@BuildableMoveConstraint
@NotMoveRoadConstraint
public record BuildableMoveDto(
        @NotNull Long id,
        @NotNull Integer x,
        @NotNull Integer y
) {}
