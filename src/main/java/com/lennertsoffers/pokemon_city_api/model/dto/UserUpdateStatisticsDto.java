package com.lennertsoffers.pokemon_city_api.model.dto;

import javax.validation.constraints.NotNull;

/**
 * <p>DTO containing the data needed to update the statistics of a user</p>
 * @param sessionTime The time that has to be added to the current play time
 */
public record UserUpdateStatisticsDto(
        @NotNull Integer sessionTime
) {}
