package com.lennertsoffers.pokemon_city_api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BuildableBuildLockedValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BuildableBuildLockedConstraint {
    String message() default "Your level is to low to build this building";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
