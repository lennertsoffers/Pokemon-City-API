package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.type.SpecialisationType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class EmployedCitizenDto extends CitizenDto {
    private final Long companyId;
    private final String companyName;

    public EmployedCitizenDto(Long id, String name, int levelSpeed, LocalDateTime assignedSince, Map<SpecialisationType, Integer> specialisationData, Map<SpecialisationType, Integer> maxSpecialisationData, Long companyId, String companyName) {
        super(id, name, levelSpeed, assignedSince, specialisationData, maxSpecialisationData);
        this.companyId = companyId;
        this.companyName = companyName;
    }
}
