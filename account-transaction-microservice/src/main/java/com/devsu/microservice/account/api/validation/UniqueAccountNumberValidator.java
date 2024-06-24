package com.devsu.microservice.account.api.validation;

import com.devsu.microservice.account.api.repository.AccountRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueAccountNumberValidator implements ConstraintValidator<UniqueAccountNumber, String> {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public boolean isValid(String accountNumber, ConstraintValidatorContext constraintValidatorContext) {
        return !accountRepository.existsByAccountNumber(accountNumber);
    }
}
