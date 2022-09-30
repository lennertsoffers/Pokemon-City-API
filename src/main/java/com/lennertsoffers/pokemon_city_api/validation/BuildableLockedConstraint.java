package com.lennertsoffers.pokemon_city_api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BuildableLockedValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BuildableLockedConstraint {
    String message() default "Your level is to low to build this building";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
