package com.example.demo.Validator;

import com.example.demo.signUp.SignUpRequest;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class CredentialValidator {

    private SignUpRequest request;

    public boolean test(String phoneNumber) {

            String regex = "(0/91)?[7-9][0-9]{9}";
            if(!phoneNumber.matches(regex)){
                throw new IllegalStateException("Phone Number invalid");
            }
            return phoneNumber.matches(regex);

    }
    public boolean emailTest(String email){
        String regex="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
        if(!email.matches(regex)){
            throw new IllegalStateException("Invalid Email");

        }
        return email.matches(regex);

    }
}
