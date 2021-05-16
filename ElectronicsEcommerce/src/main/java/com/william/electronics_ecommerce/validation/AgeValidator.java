package com.william.electronics_ecommerce.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class AgeValidator implements ConstraintValidator<Age, LocalDate> {
    private int min;
    private int max;

    @Override
    public void initialize(Age constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if (localDate == null) {
            return true;
        }

        Period period = Period.between(localDate, LocalDate.now());
        Integer age = period.getYears();

        if (age < min || age > max)
            return false;

        return true;
    }
}
