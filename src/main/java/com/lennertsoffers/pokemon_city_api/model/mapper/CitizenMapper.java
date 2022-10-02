package com.lennertsoffers.pokemon_city_api.model.mapper;

import com.lennertsoffers.pokemon_city_api.model.Citizen;
import com.lennertsoffers.pokemon_city_api.model.dto.CitizenDto;
import com.lennertsoffers.pokemon_city_api.model.dto.EmployedCitizenDto;
import org.springframework.stereotype.Component;

@Component
public class CitizenMapper {
    public CitizenDto toCitizenDto(Citizen citizen) {
        if (citizen.getCompany() == null) {
            return new CitizenDto(
                    citizen.getId(),
                    citizen.getName(),
                    citizen.getLevelSpeed(),
                    citizen.getAssignedSince(),
                    citizen.getSpecialisationData(),
                    citizen.getMaxSpecialisationData()
            );
        }

        return new EmployedCitizenDto(
                citizen.getId(),
                citizen.getName(),
                citizen.getLevelSpeed(),
                citizen.getAssignedSince(),
                citizen.getSpecialisationData(),
                citizen.getMaxSpecialisationData(),
                citizen.getCompany().getId(),
                citizen.getCompany().getName()
        );
    }
}
