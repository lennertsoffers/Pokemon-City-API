package com.lennertsoffers.pokemon_city_api.model.mapper;

import com.lennertsoffers.pokemon_city_api.model.dto.CompanyDataDto;
import com.lennertsoffers.pokemon_city_api.model.dto.DecorationDataDto;
import com.lennertsoffers.pokemon_city_api.model.dto.HouseDataDto;
import com.lennertsoffers.pokemon_city_api.model.type.CompanyType;
import com.lennertsoffers.pokemon_city_api.model.type.DecorationType;
import com.lennertsoffers.pokemon_city_api.model.type.HouseType;
import org.springframework.stereotype.Component;

/**
 * <p>Mapper to map TypeData to TypeDataDtos</p>
 */
@Component
public class TypeDataMapper {
    /**
     * <p>Maps a HouseType object to a HouseDataDto object</p>
     * @param houseType The HouseType that has to be mapped
     * @return The HouseDataDto object created from the HouseType
     * @see HouseDataDto
     */
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
                houseType.getRentPerMinute(),
                houseType.getSpritesheetLocation()
        );
    }

    /**
     * <p>Maps a CompanyType object to a CompanyDataDto object</p>
     * @param companyType The CompanyType that has to be mapped
     * @return The CompanyDataDto object created from the CompanyType
     * @see CompanyDataDto
     */
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
                companyType.getMaxAssignedCitizens(),
                companyType.getSpritesheetLocation()
        );
    }

    /**
     * <p>Maps a DecorationType object to a DecorationDataDto object</p>
     * @param decorationType The DecorationType that has to be mapped
     * @return The DecorationDataDto object created from the DecorationType
     * @see DecorationDataDto
     */
    public DecorationDataDto toDecorationDataDto(DecorationType decorationType) {
        return new DecorationDataDto(
                decorationType.getName(),
                decorationType.getSatisfactionModifier(),
                decorationType.getXpWhenFinished(),
                decorationType.getPrice(),
                decorationType.getUnlockedAtLevel(),
                decorationType.getWidth(),
                decorationType.getHeight(),
                decorationType.getSpritesheetLocation()
        );
    }
}
