package com.lennertsoffers.pokemon_city_api.validation;

import com.lennertsoffers.pokemon_city_api.model.Buildable;
import com.lennertsoffers.pokemon_city_api.model.House;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildableDemolishDto;
import com.lennertsoffers.pokemon_city_api.service.BuildableService;
import com.lennertsoffers.pokemon_city_api.service.CitizenService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <b>Validator for demolishing a buildable</b>
 * <p>Valid for not houses if the buildable is found</p>
 * <p>
 *     Valid for houses if the buildable is found and an array of id's is provided corresponding to distinct citizens that belong to the user.
 *     This array must have the same amount of distinct elements thant the citizens belonging to the house
 * </p>
 *
 */
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
            if (buildableDemolishDto.citizenIds().stream().distinct().count() != house.getNumberOfCitizens()) return false;

            return buildableDemolishDto.citizenIds().stream()
                    .distinct()
                    .allMatch(citizenService::belongsToUser);
        }

        return true;
    }
}
