package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.validation.BuildableDemolishConstraint;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>DTO containing data to demolish a buildable</p>
 * @param buildableId The id of the buildable that has to be demolished
 * @param citizenIds List of id's of citizens that have to be removed when a house gets deleted
 */
@BuildableDemolishConstraint
public record BuildableDemolishDto(
        @NotNull Long buildableId,
        List<Long> citizenIds
) {}
