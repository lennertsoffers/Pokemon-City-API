package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.validation.BuildableDemolishConstraint;

import javax.validation.constraints.NotNull;
import java.util.List;

@BuildableDemolishConstraint
public record BuildableDemolishDto(
        @NotNull Long buildableId,
        List<Long> citizenIds
) {}
