package com.lennertsoffers.pokemon_city_api.validation;

import com.lennertsoffers.pokemon_city_api.model.Buildable;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildableMoveDto;
import com.lennertsoffers.pokemon_city_api.model.type.BuildableTypeEnum;
import com.lennertsoffers.pokemon_city_api.service.BuildableService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class NotMoveRoadValidator implements ConstraintValidator<NotMoveRoadConstraint, BuildableMoveDto>  {
    private final BuildableService buildableService;

    @Override
    public void initialize(NotMoveRoadConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(BuildableMoveDto buildableMoveDto, ConstraintValidatorContext constraintValidatorContext) {
        Buildable buildable = this.buildableService.getById(buildableMoveDto.id());
        if (buildable == null) return false;
        if (buildable.getBuildableTypeEnum().equals(BuildableTypeEnum.ROAD)) return false;
        return true;
    }
}
