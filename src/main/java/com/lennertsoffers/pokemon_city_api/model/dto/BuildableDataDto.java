package com.lennertsoffers.pokemon_city_api.model.dto;

import java.util.List;

public record BuildableDataDto(
        List<HouseDataDto> houses,
        List<CompanyDataDto> companies,
        List<DecorationDataDto> decorations
) {}
