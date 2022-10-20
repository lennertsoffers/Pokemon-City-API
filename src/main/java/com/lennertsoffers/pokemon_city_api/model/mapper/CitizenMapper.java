package com.lennertsoffers.pokemon_city_api.model.mapper;

import com.lennertsoffers.pokemon_city_api.model.Citizen;
import com.lennertsoffers.pokemon_city_api.model.dto.CitizenDto;
import com.lennertsoffers.pokemon_city_api.model.dto.EmployedCitizenDto;
import org.springframework.stereotype.Component;

/**
 * <p>Mapper to map Citizens to CitizenDtos</p>
 */
@Component
public class CitizenMapper {
    /**
     * <p>Maps a Citizen object to a CitizenDto object</p>
     * <p>If the Citizen is employed, a EmployedCitizenDto is returned, a CitizenDto otherwise</p>
     * @param citizen The Citizen that has to be mapped
     * @return The CitizenDto object created from the Citizen
     * @see CitizenDto
     */
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
