package com.example.demo.appuser;

import com.example.demo.Constants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with PhoneNumber %s is not found";

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public AppUser loadUserByUsername(String phoneNumber)
            throws UsernameNotFoundException {
        try {
            return appUserRepository.findByPhoneNumber(phoneNumber).get();
        }

        catch (Exception e) {
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,phoneNumber));


        }
        //.orElseThrow(() ->
        //      new UsernameNotFoundException(
        //            String.format(USER_NOT_FOUND_MSG, phoneNumber)));
    }

    public Map<String,String> signUpUser(AppUser appUser) {
        boolean userExist = appUserRepository
                .findByPhoneNumber(appUser.getPhoneNumber())
                .isPresent();

        if (userExist) {

            Map<String,String> frs=new HashMap<>();
            frs.put("message","Already Registered");
            return frs;

           // throw new IllegalStateException("PhoneNumber already registered");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        //Map<String,String> frs=generateJWTToken(appUser);
        Map<String,String>frs=new HashMap<>();
        frs.put("message","Registration Successful");

        appUserRepository.save(appUser);

        return frs;
    }




}
