package com.lennertsoffers.pokemon_city_api.validation;

import com.lennertsoffers.pokemon_city_api.model.dto.BuildableBuildDto;
import com.lennertsoffers.pokemon_city_api.model.type.*;
import com.lennertsoffers.pokemon_city_api.service.UserService;
import com.lennertsoffers.pokemon_city_api.util.GeometryUtils;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class BuildableBuildValidator implements ConstraintValidator<BuildableBuildConstraint, BuildableBuildDto> {
    private final UserService userService;

    @Override
    public void initialize(BuildableBuildConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(BuildableBuildDto buildableBuildDto, ConstraintValidatorContext constraintValidatorContext) {
        BuildableType buildableType = switch (BuildableTypeEnum.valueOf(buildableBuildDto.buildableType())) {
            case HOUSE -> HouseType.valueOf(buildableBuildDto.name());
            case COMPANY -> CompanyType.valueOf(buildableBuildDto.name());
            case DECORATION -> DecorationType.valueOf(buildableBuildDto.name());
        };

        return userService
                .getAuthUser()
                .getCity()
                .getBuildables()
                .stream()
                .noneMatch(b -> GeometryUtils.collidesWith(b, buildableBuildDto.x(), buildableBuildDto.y(), buildableType.getWidth(), buildableType.getHeight()));
    }
}
