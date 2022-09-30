package com.lennertsoffers.pokemon_city_api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BuildableBuildEnoughFundsValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BuildableBuildEnoughFundsConstraint {
    String message() default "You don't have enough funds to build this";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
