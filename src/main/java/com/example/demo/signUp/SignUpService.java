package com.example.demo.signUp;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRole;
import com.example.demo.appuser.AppUserService;
import com.example.demo.signUp.PhoneNumberValidator;
import com.example.demo.signUp.SignUpRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignUpService {

    private final AppUserService appUserService;
    private final PhoneNumberValidator phoneNumberValidator;
    public String register(SignUpRequest request) {

        phoneNumberValidator.
                test(request.getPhoneNumber());

        appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPhoneNumber(),
                        request.getDOB(),
                        request.getPassword(),
                        AppUserRole.USER

                )
        );
        return "User registered Successfully";

    }


}
