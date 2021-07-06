package com.example.demo.appuser;

import com.example.demo.Constants;
import com.example.demo.signUp.SignUpRequest;
import com.example.demo.signin.SignInController;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
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

        if (!userExist) {

            throw new IllegalStateException("PhoneNumber already registered");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        Map<String,String> frs=generateJWTToken(appUser);

        appUserRepository.save(appUser);

        return frs;
    }

    public Map<String,String> generateJWTToken(AppUser appUser){
        long timeStamp=System.currentTimeMillis();
        String token= Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY).
                setIssuedAt(new Date(timeStamp))
                .setExpiration(new Date(timeStamp+Constants.TOKEN_VALIDITY))
                .claim("firstName",appUser.getFirstName())
                .claim("lastName",appUser.getLastName())
                .claim("phoneNumber",appUser.getPhoneNumber())
                .claim("email",appUser.getEmail())
                .claim("DOB",appUser.getDOB())
                .compact();

        Map<String,String> frs=new HashMap<>();
        frs.put("Token",token);
        return frs;

    }


}
