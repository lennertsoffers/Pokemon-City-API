package com.lennertsoffers.pokemon_city_api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BuildRoadsValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface BuildRoadsConstraint {
    String message() default "You cannot build here";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}