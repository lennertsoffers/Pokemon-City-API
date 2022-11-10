package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.Location;
import com.lennertsoffers.pokemon_city_api.model.SpritesheetLocation;
import com.lennertsoffers.pokemon_city_api.model.type.BuildableTypeEnum;
import com.lennertsoffers.pokemon_city_api.model.type.CompanyType;
import com.lennertsoffers.pokemon_city_api.model.type.SpecialisationType;
import lombok.Getter;

@Getter
public class CompanyPlacementDto extends BuildablePlacementDto {
    private final SpecialisationType specialisationType;

    public CompanyPlacementDto(Long id, Location location, BuildableTypeEnum buildableTypeEnum, SpritesheetLocation spritesheetLocation, SpecialisationType specialisationType) {
        super(id, location, buildableTypeEnum, spritesheetLocation);
        this.specialisationType = specialisationType;
    }
}
