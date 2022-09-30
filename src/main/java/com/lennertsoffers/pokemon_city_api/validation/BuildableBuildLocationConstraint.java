package com.lennertsoffers.pokemon_city_api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BuildableBuildLocationValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BuildableBuildLocationConstraint {
    String message() default "You cannot build on this location";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
