package com.lennertsoffers.pokemon_city_api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotMoveRoadValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotMoveRoadConstraint {
    String message() default "You cannot move roads";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}