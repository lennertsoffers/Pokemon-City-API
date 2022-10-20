package com.lennertsoffers.pokemon_city_api.model.dto;

import java.util.List;

/**
 * <p>DTO containing all the important data about the static data of a Buildables separated in different subtypes</p>
 * @param houses List of HouseDataDtos
 * @param companies List of CompanyDataDtos
 * @param decorations List of DecorationDataDtos
 */
public record BuildableDataDto(
        List<HouseDataDto> houses,
        List<CompanyDataDto> companies,
        List<DecorationDataDto> decorations
) {}
