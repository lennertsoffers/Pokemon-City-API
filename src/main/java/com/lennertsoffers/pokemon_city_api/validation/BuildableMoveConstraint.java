package com.lennertsoffers.pokemon_city_api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BuildableMoveValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BuildableMoveConstraint {
    String message() default "You cannot move buildings to this location";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
