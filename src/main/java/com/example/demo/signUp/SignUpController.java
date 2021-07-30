package com.example.demo.signUp;

import com.example.demo.Constants;
import com.example.demo.signUp.SignUpRequest;
import com.example.demo.signUp.SignUpService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/public")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class SignUpController {

    private final SignUpService signUpService;

    @PostMapping("/signUp")
    public ResponseEntity<Map<String,String>> register(@RequestBody SignUpRequest request) {

        Map<String,String> frs=signUpService.register(request);
        return new ResponseEntity<>(frs, HttpStatus.OK);
    }

    /*public Map<String,String> generateJWTToken(SignUpRequest request){
        long timeStamp=System.currentTimeMillis();
        String token= Jwts.builder().signWith(SignatureAlgorithm.ES256, Constants.API_SECRET_KEY).
                setIssuedAt(new Date(timeStamp))
                .setExpiration(new Date(timeStamp+Constants.TOKEN_VALIDITY))
                .claim("firstName",request.getFirstName())
                .claim("lastName",request.getLastName())
                .claim("phoneNumber",request.getPhoneNumber())
                .claim("email",request.getEmail())
                .claim("DOB",request.getDOB())
                .compact();

        Map<String,String> frs=new HashMap<>();
        frs.put("Token",token);
        return frs;

    }*/

}
