package com.lennertsoffers.pokemon_city_api.model.dto;

import javax.validation.constraints.NotNull;

public record UserUpdateStatisticsDto(
        @NotNull Integer sessionTime
) {}
