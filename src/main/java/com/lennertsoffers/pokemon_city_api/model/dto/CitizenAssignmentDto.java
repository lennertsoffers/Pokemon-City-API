package com.lennertsoffers.pokemon_city_api.model.dto;

import javax.validation.constraints.NotNull;

/**
 * <p>DTO containing the data needed to assign a citizen to a company</p>
 * @param citizenId The id of the citizen that needs to be assigned
 * @param companyId The id of the company where the citizen needs to be assigned
 */
public record CitizenAssignmentDto(
        @NotNull Long citizenId,
        @NotNull Long companyId
) {}
