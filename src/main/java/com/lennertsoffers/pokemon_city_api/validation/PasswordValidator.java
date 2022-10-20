package com.lennertsoffers.pokemon_city_api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <b>Validator for the password</b>
 * <p>
 *     Valid if the password contains:
 *     <ul>
 *         <li>At least 3 lowercase letters</li>
 *         <li>At least 1 uppercase letter</li>
 *         <li>At least 2 numbers</li>
 *         <li>At least 1 specials character</li>
 *     </ul>
 * </p>
 */
public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {
    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext ctx) {
        return password.matches("^(?=(.*[a-z]){3,})(?=(.*[A-Z])+)(?=(.*[0-9]){2,})(?=(.*[!@#$%^&*()\\-_+.])+).{6,}$");
    }
}
