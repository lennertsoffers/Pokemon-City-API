package com.lennertsoffers.pokemon_city_api.validation;

import com.lennertsoffers.pokemon_city_api.model.Buildable;
import com.lennertsoffers.pokemon_city_api.model.House;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildableDemolishDto;
import com.lennertsoffers.pokemon_city_api.service.BuildableService;
import com.lennertsoffers.pokemon_city_api.service.CitizenService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class BuildableDemolishValidator implements ConstraintValidator<BuildableDemolishConstraint, BuildableDemolishDto> {
    private final BuildableService buildableService;
    private final CitizenService citizenService;

    @Override
    public void initialize(BuildableDemolishConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(BuildableDemolishDto buildableDemolishDto, ConstraintValidatorContext constraintValidatorContext) {
        Buildable buildable = buildableService.getById(buildableDemolishDto.buildableId());

        if (buildable == null) return false;
        if (buildable instanceof House house) {
            if (buildableDemolishDto.citizenIds() == null) return false;
            if (buildableDemolishDto.citizenIds().size() != house.getNumberOfCitizens()) return false;

            return buildableDemolishDto.citizenIds().stream()
                    .distinct()
                    .allMatch(citizenService::belongsToUser);
        }

        return true;
    }
}
