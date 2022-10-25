package com.lennertsoffers.pokemon_city_api.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public record UserFilterDto(
        @Size(min = 1) String username,
        @Min(0) Integer rank,
        @Min(1) Integer level,
        @Min(0) Integer build,
        @Min(0) Integer score,
        @Min(0) Integer totalValue
) {}
