package com.devsu.microservice.account.api.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = UniqueAccountNumberValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueAccountNumber {
    String message() default "Una cuenta con este número ya existe";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
