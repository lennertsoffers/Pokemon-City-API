package com.lennertsoffers.pokemon_city_api.model.dto;

import javax.validation.constraints.NotNull;

public record CitizenAssignmentDto(
        @NotNull Long citizenId,
        @NotNull Long companyId
) {}
