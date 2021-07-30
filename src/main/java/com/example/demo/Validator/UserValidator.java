package com.example.demo.Validator;

import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

public class UserValidator implements OAuth2TokenValidator<Jwt> {

    private String app_user;
    @Override
    public OAuth2TokenValidatorResult validate(Jwt jwt) {
        return null;
    }
}
