package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.validation.BuildableMoveConstraint;

import javax.validation.constraints.NotNull;

@BuildableMoveConstraint
public record BuildableMoveDto(
        @NotNull Long id,
        @NotNull Integer x,
        @NotNull Integer y
) {}
