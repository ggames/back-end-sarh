package com.fich.sarh.auth.Infrastructure.adapter.validation;

import com.fich.sarh.auth.Application.ports.output.persistence.UserRetrievePort;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

   private final UserRetrievePort userRetrievePort;
    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if(username == null || username.isBlank()){
            return  true;
        }
        return !userRetrievePort.existsUsername(username);
    }
}
