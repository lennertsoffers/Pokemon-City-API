package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.validation.BuildableMoveConstraint;

@BuildableMoveConstraint
public record BuildableMoveDto(
        Long id,
        int x,
        int y
) {}
