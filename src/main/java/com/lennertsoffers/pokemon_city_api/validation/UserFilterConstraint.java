package com.lennertsoffers.pokemon_city_api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserFilterValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserFilterConstraint {
    String message() default "Use format 'filter=field>n' for filtering";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
