package com.lennertsoffers.pokemon_city_api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BuildableTypeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface BuildableTypeConstraint {
    String message() default "This is not a valid buildable type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
