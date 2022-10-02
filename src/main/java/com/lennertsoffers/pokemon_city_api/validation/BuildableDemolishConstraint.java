package com.lennertsoffers.pokemon_city_api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BuildableDemolishValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BuildableDemolishConstraint {
    String message() default "You cannot remove this building with this list of citizens";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
