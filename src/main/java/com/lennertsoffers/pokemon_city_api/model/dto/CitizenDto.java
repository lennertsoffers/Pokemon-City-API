package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.type.SpecialisationType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@AllArgsConstructor
public class CitizenDto {
    private final Long id;
    private final String name;
    private final int levelSpeed;
    private final LocalDateTime assignedSince;
    private final Map<SpecialisationType, Integer> specialisationData;
    private final Map<SpecialisationType, Integer> maxSpecialisationData;
}