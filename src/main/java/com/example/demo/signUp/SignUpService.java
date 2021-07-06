package com.example.demo.signUp;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRole;
import com.example.demo.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class SignUpService {

    private final AppUserService appUserService;
    private final CredentialValidator credentialValidator;
    public Map<String,String> register(SignUpRequest request) {

        credentialValidator.
                test(request.getPhoneNumber());
        credentialValidator.
                emailTest(request.getEmail());

        Map<String,String> frs= appUserService.signUpUser(
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
        return frs;

    }


}
