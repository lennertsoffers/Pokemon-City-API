package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.validation.BuildableMoveConstraint;
import com.lennertsoffers.pokemon_city_api.validation.NotMoveRoadConstraint;

import javax.validation.constraints.NotNull;

@BuildableMoveConstraint
@NotMoveRoadConstraint
public record BuildableMoveDto(
        @NotNull Long id,
        @NotNull Integer x,
        @NotNull Integer y
) {}
