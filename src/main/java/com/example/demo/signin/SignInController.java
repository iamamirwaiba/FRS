package com.example.demo.signin;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path= "api/v1/public")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class SignInController {

    private final SignInService signInService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/signIn")
    public ResponseEntity<Map<String,String>> signIn(@RequestBody Map<String, Object> userMap) throws Exception {
        String phoneNumber = (String) userMap.get("phoneNumber");
        String password = (String) userMap.get("password");
        //String phoneNumber1="9869804169";
        //String password1="test123";
        Map<String,String> frs = signInService.signIn(phoneNumber, password);

        return new ResponseEntity<>(frs, HttpStatus.OK);

        }
    /*public ResponseEntity<Map<String,String>> signIn(@RequestBody SignInRequest request) {


        String message= SignInService.signIn(request);
        Map<String, String> frs = new HashMap<>();
        frs.put("message", message);
        return new ResponseEntity<>(frs, HttpStatus.OK);
    }









    /* private SignInService signInService;

    public String signIn(@RequestBody SignInRequest request){
        // return signInService.loadUserByUsername(request.getPhoneNumber());
        return request.getPhoneNumber()+request.getPassword(); */








}
