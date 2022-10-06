package com.lennertsoffers.pokemon_city_api.model.mapper;

import com.lennertsoffers.pokemon_city_api.model.dto.CompanyDataDto;
import com.lennertsoffers.pokemon_city_api.model.dto.DecorationDataDto;
import com.lennertsoffers.pokemon_city_api.model.dto.HouseDataDto;
import com.lennertsoffers.pokemon_city_api.model.type.CompanyType;
import com.lennertsoffers.pokemon_city_api.model.type.DecorationType;
import com.lennertsoffers.pokemon_city_api.model.type.HouseType;
import org.springframework.stereotype.Component;

@Component
public class TypeDataMapper {
    public HouseDataDto toHouseDataDto(HouseType houseType) {
        return new HouseDataDto(
                houseType.getName(),
                houseType.getSatisfactionModifier(),
                houseType.getXpWhenFinished(),
                houseType.getPrice(),
                houseType.getUnlockedAtLevel(),
                houseType.getWidth(),
                houseType.getHeight(),
                houseType.getNumberOfCitizens(),
                houseType.getMaxRent(),
                houseType.getRentPerMinute()
        );
    }

    public CompanyDataDto toCompanyDataDto(CompanyType companyType) {
        return new CompanyDataDto(
                companyType.getName(),
                companyType.getSatisfactionModifier(),
                companyType.getXpWhenFinished(),
                companyType.getPrice(),
                companyType.getUnlockedAtLevel(),
                companyType.getWidth(),
                companyType.getHeight(),
                companyType.getProfitPerMinute(),
                companyType.getSpecialisationType(),
                companyType.getMaxAssignedCitizens()
        );
    }

    public DecorationDataDto toDecorationDataDto(DecorationType decorationType) {
        return new DecorationDataDto(
                decorationType.getName(),
                decorationType.getSatisfactionModifier(),
                decorationType.getXpWhenFinished(),
                decorationType.getPrice(),
                decorationType.getUnlockedAtLevel(),
                decorationType.getWidth(),
                decorationType.getHeight()
        );
    }
}
