package com.lennertsoffers.pokemon_city_api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsernameAlreadyExistsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameAlreadyExistsConstraint {
    String message() default "This username already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
