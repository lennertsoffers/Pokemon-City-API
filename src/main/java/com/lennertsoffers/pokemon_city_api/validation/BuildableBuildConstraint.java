package com.lennertsoffers.pokemon_city_api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BuildableBuildValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BuildableBuildConstraint {
    String message() default "This is not a valid buildable type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
